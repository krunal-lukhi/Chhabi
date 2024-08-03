package com.gj4.chhabi.fwk.upload;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public class UploadResponse {

    private String url;
    private String identifier;
    private double size;

    public UploadResponse() {
    }

    public UploadResponse(String identifier) {
        this.identifier = identifier;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
