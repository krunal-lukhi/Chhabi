package com.gj4.chhabi.ext.commons;

import com.gj4.chhabi.fwk.upload.UploadRequest;
import com.gj4.chhabi.fwk.upload.UploadResponse;
import com.gj4.chhabi.model.CloudStorageMetadata;

import java.io.IOException;

/**
 * @author Krunal Lukhi
 * @since 28/07/24
 */
public interface CloudStorageService {

    UploadResponse uploadFile(UploadRequest uploadRequest) throws IOException;

    UploadResponse uploadFile(UploadRequest uploadRequest, CloudStorageMetadata cloudStorageMetadata) throws IOException;

    String storageProvider();
}
