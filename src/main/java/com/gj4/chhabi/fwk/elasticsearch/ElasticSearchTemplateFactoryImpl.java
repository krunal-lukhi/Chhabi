package com.gj4.chhabi.fwk.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.elasticsearch.client.RestClient;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
@Service
public class ElasticSearchTemplateFactoryImpl implements ElasticSearchTemplateFactory {

    private final ElasticSearchClientFactory elasticSearchClientFactory;
    private ElasticsearchTemplate elasticsearchTemplate;

    public ElasticSearchTemplateFactoryImpl(ElasticSearchClientFactory elasticSearchClientFactory) {
        this.elasticSearchClientFactory = elasticSearchClientFactory;
    }

    @Override
    public ElasticsearchTemplate getTemplate() {
        if (elasticsearchTemplate == null) {
            RestClient client = elasticSearchClientFactory.getClient();
            RestClientTransport restClientTransport = new RestClientTransport(client, new JacksonJsonpMapper());
            ElasticsearchClient elasticsearchClient = new ElasticsearchClient(restClientTransport);
            return new ElasticsearchTemplate(elasticsearchClient);
        }
        return elasticsearchTemplate;
    }
}
