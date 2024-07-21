package com.gj4.chhabi.fwk.elasticsearch;

import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public interface ElasticSearchTemplateFactory {
    ElasticsearchTemplate getTemplate();
}
