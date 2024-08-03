package com.gj4.chhabi.util;

import java.util.Collection;

/**
 * @author Krunal Lukhi
 * @since 03/08/24
 */
public class ChhabiCollectionUtil {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> T firstElement(Collection<T> collection) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.iterator().next();
    }
}
