package com.gj4.chhabi.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
public class ChabbiStringUtils extends StringUtils {

    public static boolean isBlank(String input) {
        return StringUtils.isBlank(input);
    }

    public static boolean isNotBlank(String input) {
        return !isBlank(input);
    }
}
