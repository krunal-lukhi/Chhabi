package com.gj4.chhabi.fwk.elasticsearch;

import com.gj4.chhabi.fwk.FwkUtils;
import com.gj4.chhabi.fwk.crud.CrudService;
import com.gj4.chhabi.model.ESEntity;
import com.gj4.chhabi.util.ChhabiStringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        if (ChhabiStringUtils.isBlank(id)) {
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

    public List<T> findAll() {
        ElasticsearchTemplate template = elasticSearchTemplateFactory.getTemplate();
        Document annotation = AnnotationUtils.findAnnotation(clazz, Document.class);
        SearchHits<T> search = template.search(Query.findAll(), clazz, IndexCoordinates.of(annotation.indexName()));
        List<SearchHit<T>> searchHits = search.getSearchHits();
        if (searchHits != null) {
            List<T> results = searchHits.stream().map(t -> t.getContent()).collect(Collectors.toList());
            return results;
        }
        return Collections.emptyList();
    }

}
