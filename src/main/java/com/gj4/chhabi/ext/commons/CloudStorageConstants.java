package com.gj4.chhabi.ext.commons;

/**
 * @author TedaMeda
 * @since 8/3/2024
 */
public interface CloudStorageConstants {
    interface GoogleConstants {
        interface Fields {
            String id = "id";
            String name = "name";
            String webContentLink = "webContentLink";
            String webViewLink = "webViewLink";
        }
        interface Role {
            String writer = "writer";
            String reader = "reader";

        }
        interface Access {
            String user = "user";
            String anyone = "anyone";
        }
    }
}
