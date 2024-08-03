package com.gj4.chhabi.fwk.mongo;

import com.gj4.chhabi.fwk.FwkUtils;
import com.gj4.chhabi.fwk.crud.CrudService;
import com.gj4.chhabi.model.BaseEntity;
import com.gj4.chhabi.model.MongoEntity;
import com.gj4.chhabi.util.ChhabiStringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */

public class MongoService<T extends MongoEntity> implements CrudService<T> {
    private final MongoTemplateFactory mongoTemplateFactory;
    private final Class<T> clazz;

    public MongoService(MongoTemplateFactory mongoTemplateFactory, Class<T> clazz) {
        this.mongoTemplateFactory = mongoTemplateFactory;
        this.clazz = clazz;
    }

    public T create(T object) {
        String id = object.getId();
        if (ChhabiStringUtils.isBlank(id)) {
            object.setId(FwkUtils.newObjectId());
        }
        Document document = AnnotationUtils.findAnnotation(clazz, Document.class);

        MongoTemplate mongoTemplate = mongoTemplateFactory.getTemplate();
        mongoTemplate.save(object, getCollection(document));
        return object;
    }

    public T update(T object) {
        return create(object);
    }

    public T find(String id) {
        MongoTemplate mongoTemplate = mongoTemplateFactory.getTemplate();
        Document document = AnnotationUtils.findAnnotation(clazz, Document.class);
        T object = mongoTemplate.findById(id, clazz, getCollection(document));
        return object;
    }

    public void delete(String id) {
        MongoTemplate mongoTemplate = mongoTemplateFactory.getTemplate();
        Query query = Query.query(Criteria.where(BaseEntity.ID).is(id));
        mongoTemplate.remove(query, clazz);
    }

    public List<T> search(Query query) {
        MongoTemplate mongoTemplate = mongoTemplateFactory.getTemplate();
        return mongoTemplate.find(query, clazz);
    }

    /***********************************************************************************************************
     *                                           PRIVATE METHODS                                                                            *
     ***********************************************************************************************************/

    private String getCollection(Document document) {
        String collectionName = document.collection();
        if (ChhabiStringUtils.isBlank(collectionName)) {
            return ChhabiStringUtils.convertToCamelCase(this.clazz.getSimpleName());
        }
        return collectionName;
    }

}
