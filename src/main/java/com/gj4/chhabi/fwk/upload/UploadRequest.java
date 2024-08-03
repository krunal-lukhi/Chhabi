package com.gj4.chhabi.fwk.upload;

import com.gj4.chhabi.ext.commons.MimeType;

import java.io.File;
import java.io.InputStream;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public class UploadRequest {

    private InputStream inputStream;
    private String fileName;
    private MimeType fileType;
    private File file;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MimeType getFileType() {
        return fileType;
    }

    public void setFileType(MimeType fileType) {
        this.fileType = fileType;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
