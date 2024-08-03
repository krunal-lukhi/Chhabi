package com.gj4.chhabi.fwk.upload;

import com.gj4.chhabi.ext.commons.MimeType;

import java.io.File;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public class UploadRequest {

    private File file;
    private String fileName;
    private MimeType fileType;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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
}
