package com.gj4.chhabi.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */
@Document
public class CloudStorageMetadata extends BaseEntity implements MongoEntity {

    private String provider;
    private String folderId;
    private double totalSize;
    private double remainingSpace;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public double getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(double totalSize) {
        this.totalSize = totalSize;
    }

    public double getRemainingSpace() {
        return remainingSpace;
    }

    public void setRemainingSpace(double remainingSpace) {
        this.remainingSpace = remainingSpace;
    }

    public interface Fields {
        String provider = "provider";
        String remainingSpace = "remainingSpace";
    }
}
