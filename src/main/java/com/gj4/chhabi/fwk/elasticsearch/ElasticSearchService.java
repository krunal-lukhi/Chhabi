package com.gj4.chhabi.fwk.elasticsearch;

import com.gj4.chhabi.fwk.FwkUtils;
import com.gj4.chhabi.fwk.crud.CrudService;
import com.gj4.chhabi.model.ESEntity;
import com.gj4.chhabi.util.ChabbiStringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public class ElasticSearchService<T extends ESEntity> implements CrudService<T> {
    private final ElasticSearchTemplateFactory elasticSearchTemplateFactory;
    private final Class<T> clazz;

    public ElasticSearchService(ElasticSearchTemplateFactory elasticSearchTemplateFactory, Class<T> clazz) {
        this.elasticSearchTemplateFactory = elasticSearchTemplateFactory;
        this.clazz = clazz;
    }

    public T create(T object) {
        String id = object.getId();
        if (ChabbiStringUtils.isBlank(id)) {
            object.setId(FwkUtils.newObjectId());
        }
        Document annotation = AnnotationUtils.findAnnotation(clazz, Document.class);
        IndexQueryBuilder indexQueryBuilder = new IndexQueryBuilder().withId(object.getId()).withObject(object).withIndex(annotation.indexName());
        ElasticsearchTemplate template = elasticSearchTemplateFactory.getTemplate();
        template.index(indexQueryBuilder.build(), IndexCoordinates.of(annotation.indexName()));
        return object;
    }

    public T update(T object) {
        return create(object);
    }

    public void delete(String id) {
        ElasticsearchTemplate template = elasticSearchTemplateFactory.getTemplate();
        template.delete(id, clazz);
    }

    public T find(String id) {
        ElasticsearchTemplate template = elasticSearchTemplateFactory.getTemplate();
        T entity = template.get(id, clazz);
        return entity;
    }

}
