package com.gj4.chhabi.fwk.elasticsearch;

import com.gj4.chhabi.model.ESEntity;
import org.springframework.stereotype.Service;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
@Service
public class ElasticSearchServiceFactory {

    private final ElasticSearchTemplateFactory elasticSearchTemplateFactory;

    public ElasticSearchServiceFactory(ElasticSearchTemplateFactory elasticSearchTemplateFactory) {
        this.elasticSearchTemplateFactory = elasticSearchTemplateFactory;
    }

    public <T extends ESEntity> ElasticSearchService<T> lookup(Class<T> clazz) {
        return new ElasticSearchService(elasticSearchTemplateFactory, clazz);
    }

}
