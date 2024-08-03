package com.gj4.chhabi.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public class ChhabiStringUtils extends StringUtils {

    public static boolean isBlank(String input) {
        return StringUtils.isBlank(input);
    }

    public static boolean isNotBlank(String input) {
        return !isBlank(input);
    }

    public static String convertToCamelCase(String input) {
        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }

    public static String joinKeys(String seperator, String... keys) {
        return join(keys, seperator);
    }
}
