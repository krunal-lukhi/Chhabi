package com.gj4.chhabi.ext.commons;

import com.gj4.chhabi.fwk.upload.UploadRequest;
import com.gj4.chhabi.fwk.upload.UploadResponse;
import com.gj4.chhabi.model.CloudStorageMetadata;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Krunal Lukhi
 * @since 03/08/24
 */
public abstract class AbstractCloudStorageService implements CloudStorageService {

    @Override
    public UploadResponse uploadFile(UploadRequest uploadRequest) {
        File tempFile = getTempFile(uploadRequest);
        try {
            uploadRequest.setFile(tempFile);
            return doUpload(uploadRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            FileUtils.deleteQuietly(tempFile);
        }
    }

    @Override
    public UploadResponse uploadFile(UploadRequest uploadRequest, CloudStorageMetadata cloudStorageMetadata) throws IOException {
        File tempFile = getTempFile(uploadRequest);
        try {
            uploadRequest.setFile(tempFile);
            return doUpload(uploadRequest, cloudStorageMetadata);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            FileUtils.deleteQuietly(tempFile);
        }
    }

    protected abstract UploadResponse doUpload(UploadRequest uploadRequest, CloudStorageMetadata cloudStorageMetadata) throws IOException;

    protected abstract UploadResponse doUpload(UploadRequest uploadRequest) throws IOException;

    /***********************************************************************************************************
     *                                           PRIVATE METHODS                                                                            *
     ***********************************************************************************************************/

    private java.io.File getTempFile(UploadRequest uploadRequest) {
        final java.io.File tempFile;
        try {
            tempFile = downloadToTempFile(uploadRequest.getInputStream(), uploadRequest.getFileName());
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }finally {
            IOUtils.closeQuietly(uploadRequest.getInputStream());
        }
        return tempFile;
    }

    private java.io.File downloadToTempFile(InputStream inputStream, String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            java.io.File tmpFile = java.io.File.createTempFile(fileName, "tmp");
            tmpFile.setWritable(true);
            tmpFile.deleteOnExit();
            fileOutputStream = new FileOutputStream(tmpFile);
            IOUtils.copyLarge(inputStream, fileOutputStream, new byte[1024 * 8]);
            fileOutputStream.flush();
            return tmpFile;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
            IOUtils.closeQuietly(inputStream);
        }
    }

}
