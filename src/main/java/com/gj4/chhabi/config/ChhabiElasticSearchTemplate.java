package com.gj4.chhabi.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public class ChhabiElasticSearchTemplate extends ElasticsearchTemplate {
    public ChhabiElasticSearchTemplate(ElasticsearchClient client) {
        super(client);
    }
}
