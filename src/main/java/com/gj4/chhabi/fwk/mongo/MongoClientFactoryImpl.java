package com.gj4.chhabi.fwk.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoDriverInformation;
import com.mongodb.client.MongoClient;
import com.mongodb.client.internal.MongoClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */
@Service
public class MongoClientFactoryImpl implements MongoClientFactory{
    private MongoClient mongoClient;
    @Value("${mongodb.connection.string}")
    private String mongoConnectionString;

//    TODO: Revisite for mongo Driver Information
    @Override
    public MongoClient getClient() {
        if(mongoClient==null){
            MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(mongoConnectionString)).build();
            MongoDriverInformation mongoDriverInformation = MongoDriverInformation.builder().build();

            return mongoClient = new MongoClientImpl(mongoClientSettings, mongoDriverInformation);
        }
        return mongoClient;
    }
}
