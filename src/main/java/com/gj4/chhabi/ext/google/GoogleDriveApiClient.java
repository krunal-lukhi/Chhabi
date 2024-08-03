package com.gj4.chhabi.ext.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

/**
 * @author Krunal Lukhi
 * @since 28/07/24
 */
public class GoogleDriveApiClient {

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Chhabi_backend_server";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private static Credential getCredentials()
            throws IOException {
        // Load client secrets.
        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }

        // Build flow and trigger user authorization request.
        GoogleCredential credential = GoogleCredential.fromStream(in)
                .createScoped(Collections.singletonList(DriveScopes.DRIVE));
        //returns an authorized Credential object.
        return credential;
    }

    public Drive getDrive() {
        final NetHttpTransport HTTP_TRANSPORT;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials())
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            return drive;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
