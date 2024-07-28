package com.gj4.chhabi.fwk.mongo;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author TedaMeda
 * @since 7/28/2024
 */
public interface MongoTemplateFactory {
    MongoTemplate getTemplate();
}
