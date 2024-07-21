package com.gj4.chhabi.fwk;

import org.bson.types.ObjectId;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public class FwkUtils {
    public static String newObjectId() {
        return new ObjectId().toString();
    }
}
