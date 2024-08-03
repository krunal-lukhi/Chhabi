package com.gj4.chhabi.fwk.commons;

/**
 * @author Krunal Lukhi
 * @since 03/08/24
 */
public class StorageNotAvailableException extends RuntimeException {
    private String storageProvider;

    public StorageNotAvailableException() {
    }

    public StorageNotAvailableException(String storageProvider) {
        this.storageProvider = storageProvider;
    }

    public StorageNotAvailableException(String message, String storageProvider) {
        super(message);
        this.storageProvider = storageProvider;
    }
}
