package com.gj4.chhabi.fwk.mongo;

import com.mongodb.client.MongoClient;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */
public interface MongoClientFactory {
    MongoClient getClient();
}
