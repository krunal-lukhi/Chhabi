package com.gj4.chhabi.ext.google;

import com.gj4.chhabi.ext.commons.CloudStorageService;
import com.gj4.chhabi.ext.commons.CloundStorageProvider;
import com.gj4.chhabi.ext.commons.MimeType;
import com.gj4.chhabi.fwk.upload.UploadRequest;
import com.gj4.chhabi.fwk.upload.UploadResponse;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Krunal Lukhi
 * @since 28/07/24
 */
@Service
public class GoogleDriveService implements CloudStorageService {

    private GoogleClientFactory clientFactory;

    @Override
    public UploadResponse uploadFile(UploadRequest uploadRequest) throws IOException {
        Drive drive = clientFactory.getClient();
        File fileMetadata = FileBuilder.builder()
                .name("Test")
                .parent("1-Yo4Rw7fJZv2o1CpexDtdp-0VKkWpGgh")
                .mimeType(MimeType.FOLDER).build();

        FileContent fileContent = new FileContent(uploadRequest.getFileType(), uploadRequest.getFile());
        File file = drive.files().create(fileMetadata, fileContent)
                .setFields("id, name, webContentLink, webViewLink")
                .execute();
        setPermissionsToFile(drive, file.getId());
        return new UploadResponse(file.getId());
    }

    private void setPermissionsToFile(Drive drive, String id) throws IOException {
        Permission permission = new Permission().setType("user").setRole("writer").setEmailAddress("ronakmevada6102@gmail.com");
        drive.permissions().create(id, permission).setFields("id").execute();
    }

    @Override
    public String storageProvider() {
        return CloundStorageProvider.GOOGLE.name();
    }
}
