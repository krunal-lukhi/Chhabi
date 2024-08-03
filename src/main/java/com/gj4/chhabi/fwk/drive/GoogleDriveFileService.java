package com.gj4.chhabi.fwk.drive;

import com.gj4.chhabi.ext.google.FileBuilder;
import com.gj4.chhabi.ext.commons.MimeType;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/**
 * @author TedaMeda
 * @since 7/31/2024
 */
@Service
public class GoogleDriveFileService {
    private static final String APPLICATION_NAME = "Chhabi_backend_server";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private final Drive drive;

    public GoogleDriveFileService() throws GeneralSecurityException, IOException {
        this.drive = getService();
    }

    private Drive getService() throws IOException, GeneralSecurityException {
        InputStream in = GoogleDriveFileService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleCredential credentials = GoogleCredential.fromStream(in)
                .createScoped(Collections.singletonList(DriveScopes.DRIVE));
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credentials)
                .setApplicationName(APPLICATION_NAME).build();
        return service;
    }

    public List<File> getFiles() throws GeneralSecurityException, IOException {
        FileList result = drive.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)").execute();

        return result.getFiles();
    }

    public void createFile() throws GeneralSecurityException, IOException {
        File fileMetadata = FileBuilder.builder()
                .name("Test")
                .parent(getFiles().get(0).getId())
                .mimeType(MimeType.FOLDER).build();

        File file = drive.files().create(fileMetadata).setFields("id").execute();
        System.out.println("File ID: " + file.getId());

        Permission permission = new Permission().setType("user").setRole("writer").setEmailAddress("ronakmevada610@gmail.com");

        drive.permissions().create(file.getId(), permission).setFields("id").execute();
    }

    public <T> void createBatchInBatch(String folder, List<T> fileList){
        BatchRequest batch = drive.batch();
        JsonBatchCallback<File> fileCallBack = new JsonBatchCallback<File>() {
            @Override
            public void onFailure(GoogleJsonError googleJsonError, HttpHeaders httpHeaders) throws IOException {
            }

            @Override
            public void onSuccess(File file, HttpHeaders httpHeaders) throws IOException {

            }
        };

    }
    public boolean verifyAdmin(String fileId) throws GeneralSecurityException, IOException {
        File result = drive.files().get("1NkndUsVqjzwDa-Dcsgn77130-L5u9a0V").setFields("ownedByMe").execute();
        return result.getOwnedByMe();
    }

    public void deleteFile() throws GeneralSecurityException, IOException {
        Void file = drive.files().delete("1l3CZWAom0JRkr03aVwt78y44jx_SpcoI").execute();
    }
}
