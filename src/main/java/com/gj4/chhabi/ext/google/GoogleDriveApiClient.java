package com.gj4.chhabi.ext.google;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.util.Collections;
import java.util.List;

/**
 * @author Krunal Lukhi
 * @since 28/07/24
 */
public class GoogleDriveApiClient {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES =
            Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);

    private final String apiKey;

    public GoogleDriveApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    private HttpRequestInitializer getCredentials() {
        return new GoogleApiKeyAuthentication(apiKey);
    }

    public Drive getDrive() {
        final NetHttpTransport HTTP_TRANSPORT;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials())
                .setApplicationName("Random")
                .build();
        return drive;
    }
}
