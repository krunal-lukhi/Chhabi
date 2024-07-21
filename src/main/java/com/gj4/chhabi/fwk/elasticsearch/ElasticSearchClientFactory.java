package com.gj4.chhabi.fwk.elasticsearch;

import org.elasticsearch.client.RestClient;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public interface ElasticSearchClientFactory {
    RestClient getClient();
}
