package com.gj4.chhabi.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */
@Document
public class CloudStorageMetadata extends BaseEntity implements MongoEntity {

    private String provider;
    private String url;
    private double size;
    private String apiKey;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
