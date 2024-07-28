package com.gj4.chhabi.fwk.mongo;

import com.gj4.chhabi.fwk.FwkUtils;
import com.gj4.chhabi.model.MongoEntity;
import com.gj4.chhabi.util.ChabbiStringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */

public class MongoService<T extends MongoEntity> {
    private final MongoTemplateFactory mongoTemplateFactory;
    private final Class<T> clazz;
    public MongoService(MongoTemplateFactory mongoTemplateFactory, Class<T> clazz) {
        this.mongoTemplateFactory = mongoTemplateFactory;
        this.clazz = clazz;
    }

    public T create(T object){
        String id = object.getId();
        if(ChabbiStringUtils.isBlank(id)){
            object.setId(FwkUtils.newObjectId());
        }
        Document document = AnnotationUtils.findAnnotation(clazz, Document.class);
        MongoTemplate mongoTemplate = mongoTemplateFactory.getTemplate();
        mongoTemplate.save(object, document.collection());
        return object;
    }
    public T update(T object){
        return create(object);
    }

    public T find(String id){
        MongoTemplate mongoTemplate = mongoTemplateFactory.getTemplate();
        Document document = AnnotationUtils.findAnnotation(clazz, Document.class);
        T object = mongoTemplate.findById(id, clazz, document.collection());
        return object;
    }

    public void delete(String id){
        Document document = AnnotationUtils.findAnnotation(clazz, Document.class);
        MongoTemplate mongoTemplate = mongoTemplateFactory.getTemplate();
    }

}
