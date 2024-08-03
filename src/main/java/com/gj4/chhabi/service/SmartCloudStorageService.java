package com.gj4.chhabi.service;

import com.gj4.chhabi.fwk.upload.UploadRequest;
import com.gj4.chhabi.fwk.upload.UploadResponse;
import com.gj4.chhabi.model.CloudStorageMetadata;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @author Krunal Lukhi
 * @since 03/08/24
 */
@Service
public class SmartCloudStorageService {
    private final CloudStorageMetadataService cloudStorageMetadataService;
    private final CloudStorageServiceRegistry cloudStorageServiceRegistry;

    public SmartCloudStorageService(CloudStorageMetadataService cloudStorageMetadataService, CloudStorageServiceRegistry cloudStorageServiceRegistry) {
        this.cloudStorageMetadataService = cloudStorageMetadataService;
        this.cloudStorageServiceRegistry = cloudStorageServiceRegistry;
    }

    public UploadResponse upload(UploadRequest uploadRequest) {
        CloudStorageMetadata optimalDrive = cloudStorageMetadataService.findOptimalDrive();
        try {
            UploadResponse uploadResponse = cloudStorageServiceRegistry.get(optimalDrive.getProvider()).uploadFile(uploadRequest, optimalDrive);
            updateMetadata(optimalDrive, uploadResponse);
            return uploadResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /***********************************************************************************************************
     *                                           PRIVATE METHODS                                                                            *
     ***********************************************************************************************************/

    private void updateMetadata(CloudStorageMetadata optimalDrive, UploadResponse uploadResponse) {
        optimalDrive.setRemainingSpace(optimalDrive.getRemainingSpace() - uploadResponse.getSize());
        cloudStorageMetadataService.update(optimalDrive);
    }
}
