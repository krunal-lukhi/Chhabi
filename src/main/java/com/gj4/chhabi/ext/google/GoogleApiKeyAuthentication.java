package com.gj4.chhabi.ext.google;

import com.google.api.client.http.*;
import com.google.api.client.util.Data;

import java.io.IOException;
import java.util.Map;

/**
 * @author Krunal Lukhi
 * @since 28/07/24
 */
public final class GoogleApiKeyAuthentication implements HttpRequestInitializer, HttpExecuteInterceptor {
    private final String apiKey;
    public GoogleApiKeyAuthentication(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void intercept(HttpRequest request) throws IOException {
        Map<String, Object> data = Data.mapOf(UrlEncodedContent.getContent(request).getData());
        data.put("key", apiKey);
    }

    @Override
    public void initialize(HttpRequest request) throws IOException {
        request.setInterceptor(this);
    }
}
