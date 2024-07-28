package com.gj4.chhabi.fwk.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */
@Service
public class MongoTemplateFactoryImp implements MongoTemplateFactory {
    private MongoTemplate mongoTemplate;
    @Value("${mongodb.database}")
    private String databaseName;
    private final MongoClientFactory mongoClientFactory;

    public MongoTemplateFactoryImp(MongoClientFactory mongoClientFactory) {
        this.mongoClientFactory = mongoClientFactory;
    }

    @Override
    public MongoTemplate getTemplate() {
        if (mongoTemplate == null) {
            return mongoTemplate = new MongoTemplate(mongoClientFactory.getClient(), databaseName);
        }
        return mongoTemplate;
    }
}
