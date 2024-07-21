package com.gj4.chhabi.fwk.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
@Service
public class ElasticSearchClientFactoryImpl implements ElasticSearchClientFactory {

    @Value(" ${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private int port;
    @Value("${elasticsearch.connection.policy}")
    private String policy;

    private RestClient client;

    @Override
    public RestClient getClient() {
        if (client == null) {
            client = RestClient.builder(getHttpHost()).build();
        }
        return client;
    }

    private HttpHost getHttpHost() {
        return new HttpHost(host, port, policy);
    }
}
