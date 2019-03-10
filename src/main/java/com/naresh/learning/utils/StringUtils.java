package com.naresh.learning.utils;

public class StringUtils {
    private static final String EMPTY_STRING = "";
    private static final String SINGLE_WHITESPACE = " ";
    public static final String AND = "and";

    public static String titleCase(String input) {
        if (input == null || input.isEmpty()) {
            return EMPTY_STRING;
        } else if(input.length() == 1) {
            return input.toUpperCase();
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static void appendWithSpace(StringBuilder prefix, String toAppend) {
        if (prefix != null
                && !(toAppend == null || toAppend.isEmpty())) {
            if (prefix.length() != 0) {
                prefix.append(SINGLE_WHITESPACE);
            }
            prefix.append(toAppend);
        }
    }
}
