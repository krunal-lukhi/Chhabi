package com.gj4.chhabi.ext.google;

import com.gj4.chhabi.ext.commons.AbstractCloudStorageService;
import com.gj4.chhabi.ext.commons.CloudStorageConstants;
import com.gj4.chhabi.ext.commons.CloudStorageConstants.GoogleConstants.Fields;
import com.gj4.chhabi.ext.commons.CloudStorageService;
import com.gj4.chhabi.ext.commons.CloundStorageProvider;
import com.gj4.chhabi.fwk.commons.StorageNotAvailableException;
import com.gj4.chhabi.fwk.commons.UniversalConstants;
import com.gj4.chhabi.fwk.upload.UploadRequest;
import com.gj4.chhabi.fwk.upload.UploadResponse;
import com.gj4.chhabi.model.CloudStorageMetadata;
import com.gj4.chhabi.service.CloudStorageMetadataService;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.gj4.chhabi.ext.commons.CloudStorageConstants.GoogleConstants.Fields.*;
import static com.gj4.chhabi.util.ChhabiStringUtils.joinKeys;

/**
 * @author Krunal Lukhi
 * @since 28/07/24
 */
@Service
public class GoogleDriveService extends AbstractCloudStorageService {

    private final CloudStorageMetadataService cloudStorageMetadataService;
    private final GoogleClientFactory clientFactory;

    public GoogleDriveService(CloudStorageMetadataService cloudStorageMetadataService, GoogleClientFactory clientFactory) {
        this.cloudStorageMetadataService = cloudStorageMetadataService;
        this.clientFactory = clientFactory;
    }

    @Override
    public UploadResponse doUpload(UploadRequest uploadRequest) throws IOException {
        CloudStorageMetadata cloudStorageMetadata = cloudStorageMetadataService.findOptimalDrive(storageProvider());
        return uploadFile(uploadRequest, cloudStorageMetadata);
    }

    @Override
    public UploadResponse doUpload(UploadRequest uploadRequest, CloudStorageMetadata cloudStorageMetadata) throws IOException {
        Drive drive = clientFactory.getClient();
        File fileMetadata = createMetadata(uploadRequest, cloudStorageMetadata);

        FileContent fileContent = createContent(uploadRequest);
        File file = createFile(drive, fileMetadata, fileContent);
        Permission permission = setPermissionsToFile(drive, file);
        return convertToResponse(file, permission);
    }

    @Override
    public String storageProvider() {
        return CloundStorageProvider.GOOGLE.name();
    }

    /***********************************************************************************************************
     *                                           PRIVATE METHODS                                                                            *
     ***********************************************************************************************************/

    private UploadResponse convertToResponse(File file, Permission permission) {
        UploadResponse uploadResponse = new UploadResponse();
        uploadResponse.setIdentifier(file.getId());
        uploadResponse.setUrl(file.getWebViewLink());
        uploadResponse.setSize(file.getSize());
        return uploadResponse;
    }

    private File createFile(Drive drive, File fileMetadata, FileContent fileContent) throws IOException {
        return drive.files().create(fileMetadata, fileContent)
                .setFields(joinKeys(UniversalConstants.COMMA, id, name, size, webContentLink, webViewLink))
                .execute();
    }

    private FileContent createContent(UploadRequest uploadRequest) {
        return new FileContent(uploadRequest.getFileType().getType(), uploadRequest.getFile());
    }

    private File createMetadata(UploadRequest uploadRequest, CloudStorageMetadata cloudStorageMetadata) {
        if (cloudStorageMetadata == null) {
            throw new StorageNotAvailableException("Pela Storage to laav mafatiya", storageProvider());
        }
        return FileBuilder.builder()
                .name(uploadRequest.getFileName())
                .parent(cloudStorageMetadata.getFolderId())
                .mimeType(uploadRequest.getFileType()).build();
    }

    private Permission setPermissionsToFile(Drive drive, File file) throws IOException {
        Permission permission = new Permission().setType(CloudStorageConstants.GoogleConstants.Access.anyone).setRole(CloudStorageConstants.GoogleConstants.Role.reader);
        return drive.permissions().create(file.getId(), permission).setFields(Fields.id).execute();
    }

}
