package com.gj4.chhabi.ext.google;

import com.gj4.chhabi.ext.commons.CloundStorageProvider;
import com.gj4.chhabi.ext.commons.CloudStorageService;
import com.gj4.chhabi.fwk.upload.UploadRequest;
import com.gj4.chhabi.fwk.upload.UploadResponse;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

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
        File fileMetadata = new File();
        fileMetadata.setName(uploadRequest.getFileName());
        fileMetadata.setParents(Collections.singletonList("folderId"));
        FileContent fileContent = new FileContent(uploadRequest.getFileType(), uploadRequest.getFile());
        File file = drive.files().create(fileMetadata, fileContent)
                .setFields("id, name, webContentLink, webViewLink")
                .execute();
        setPermissionsToFile(drive, file.getId());
        return new UploadResponse(file.getId());
    }

    private void setPermissionsToFile(Drive drive, String id) {

    }

    @Override
    public String storageProvider() {
        return CloundStorageProvider.GOOGLE.name();
    }
}
