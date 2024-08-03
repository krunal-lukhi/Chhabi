package com.gj4.chhabi.ext.google;

/**
 * @author Krunal Lukhi
 * @since 28/07/24
 */
public interface CloudStorageClientFactory<Client> {

    Client getClient();
}
