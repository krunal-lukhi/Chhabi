package com.gj4.chhabi.fwk.upload;

import java.io.File;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public class UploadRequest {

    private File file;
    private String fileName;
    private String fileType;

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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
