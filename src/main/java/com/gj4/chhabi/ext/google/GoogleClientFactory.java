package com.gj4.chhabi.ext.google;

import com.gj4.chhabi.fwk.mongo.MongoService;
import com.gj4.chhabi.fwk.mongo.MongoServiceFactory;
import com.gj4.chhabi.model.CloudStorageMetadata;
import com.google.api.services.drive.Drive;
import org.springframework.stereotype.Service;

/**
 * @author Krunal Lukhi
 * @since 28/07/24
 */
@Service
public class GoogleClientFactory implements SmartCloudStorageClientFactory<Drive> {

    private final MongoService<CloudStorageMetadata> driveInfoMongoService;
    private Drive drive;

    public GoogleClientFactory(MongoServiceFactory mongoServiceFactory) {
        this.driveInfoMongoService = mongoServiceFactory.lookup(CloudStorageMetadata.class);
    }

    @Override
    public Drive getClient() {
        if (drive == null) {
            CloudStorageMetadata cloudStorageMetadata = geOptimalDrive();
            return drive = new GoogleDriveApiClient(cloudStorageMetadata.getApiKey()).getDrive();
        }
        return drive;
    }

    private CloudStorageMetadata geOptimalDrive() {
        return driveInfoMongoService.find("write query hear");
    }
}
