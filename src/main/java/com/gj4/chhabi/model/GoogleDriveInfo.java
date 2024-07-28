package com.gj4.chhabi.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */
@Document(collection = "google_drive_info")
public class GoogleDriveInfo extends BaseEntity implements MongoEntity{
    private String url;
    private double size;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
