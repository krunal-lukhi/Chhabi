package com.gj4.chhabi.ext.commons;

/**
 * @author TedaMeda
 * @since 7/31/2024
 */
public enum MimeType {
    JPEG ("image/jpeg"),
    PNG("image/png"),
    MP4("video/mp4"),
    FOLDER("application/vnd.google-apps.folder"),
    TEXT("text/plain"),
    HEIF("image/heif"),
    HEIC("image/heic");
    private final String type;

    MimeType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return this.type;
    }
}
