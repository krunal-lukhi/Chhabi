package com.gj4.chhabi.fwk.mongo;

import com.gj4.chhabi.model.ESEntity;
import com.gj4.chhabi.model.MongoEntity;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */
public class MongoServiceFactory {
    private final MongoTemplateFactory mongoTemplateFactory;

    public MongoServiceFactory(MongoTemplateFactory mongoTemplateFactory) {
        this.mongoTemplateFactory = mongoTemplateFactory;
    }

    public <T extends MongoEntity> MongoService<T> lookup(Class<T> clazz){
        return new MongoService(mongoTemplateFactory, clazz);
    }
}