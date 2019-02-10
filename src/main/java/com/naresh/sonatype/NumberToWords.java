package com.naresh.sonatype;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.naresh.sonatype.utils.StringUtils.appendWithSpace;
import static com.naresh.sonatype.utils.StringUtils.titleCase;
import static com.naresh.sonatype.utils.StringUtils.AND;

public class NumberToWords {

    private static final long THOUSAND = 1000L;
    private static final long NEGATIVE_THOUSAND = -1000L;
    private static final Map<Integer, String> PREDEFINED_NUMBER_NAMES = buildPredefinedNamingMap();
    private static final Map<Integer, String> PLACE_VALUE_NAME_MAP = buildPlaceValuesMap();

    /**
     * Entry method to transform long values to english
     * @param value input value to transform
     * @return value in english words
     */
    public static String transform(Long value) {
        StringBuilder output = new StringBuilder();
        if(value < 0) {
            output.append("Negative");
        }
        transformThousandAndAbove(value, 0, output);
        return titleCase(output.toString());
    }

    /**
     * Since number changes names on every multiple of 10^3 after thousand,
     *          we take 3 digits(every hundredth place) at a time and transform it as per its place position.
     *      Example:
     *          10^3  - Thousand
     *          10^6  - Million
     *          10^9  - Billion
     *          10^12  - Trillion
     *
     * @param value Value to transform
     * @param place current iteration's place position
     * @param output output of transformation
     */
    @VisibleForTesting
    protected static void transformThousandAndAbove(Long value, Integer place, StringBuilder output) {
        if (value == 0 && place == 0) {
            output.append("Zero");
        }

        Long currentHundredsValue = Math.abs(value % THOUSAND);
        if (value.compareTo(THOUSAND) >= 0 || value.compareTo(NEGATIVE_THOUSAND) <= 0) {
            // Since we divide by 1000, increase place position by 3
            transformThousandAndAbove(value / THOUSAND, place + 3, output);
            if (currentHundredsValue < 100 && currentHundredsValue != 0) {
                // Downstream method doesn't append 'AND' when hundredth position doesn't exist in its input
                appendWithSpace(output, AND);
            }
        }

        if (currentHundredsValue != 0) {
            transformBelowThousand(currentHundredsValue.intValue(), 0, output);
            appendWithSpace(output, PLACE_VALUE_NAME_MAP.get(place));
        }
    }

    /**
     * Namings for numbers below 1000 falls in a different pattern, hence handled differently
     *
     * @param value value to be transformed
     * @param place current place position in the iteration
     * @param output transformed output
     */
    @VisibleForTesting
    protected static void transformBelowThousand(Integer value, Integer place, StringBuilder output) {

        if (value >= THOUSAND || value < 0) {
            throw new IllegalArgumentException("Negative numbers and thousand and above are invalid input. Received: " + value);
        }

        if (value == 0 && place == 0) {
            output.append("Zero");
        }

        // Numbers up to tenth position could fall in pre-defined naming as mapped in PREDEFINED_NUMBER_NAMES map.
        if (place == 0 && PREDEFINED_NUMBER_NAMES.containsKey(value % 100)) {
            if (value >= 100) {
                transformBelowThousand(value / 100, place + 2, output);
                appendWithSpace(output, AND);
            }
            appendWithSpace(output, PREDEFINED_NUMBER_NAMES.get(value % 100));
            return;
        }

        Integer remainingValue = value / 10;

        if (value >= 10) {
            transformBelowThousand(remainingValue, place + 1, output);

            // When processing tenth place append 'AND' if hundredth place has Non-Zero value
            // When processing ones place append 'AND' if tenth place has Zero and hundredth place has Non-Zero value
            boolean includeAnd = value % 10 != 0 && (place == 1 || (place == 0 && remainingValue % 10 == 0));

            if (includeAnd) {
                appendWithSpace(output, AND);
            }
        }

        appendCurrentPlaceWord(output, value % 10, place);
    }

    private static void appendCurrentPlaceWord(StringBuilder output, Integer value, Integer place) {
        if (value == 0) {
            return; // Zero has no significance in number wording.
        } else if (place < 2) {
            // To retrieve names of 10 multiples in tenth place and single digits in ones place
            Integer currentPlaceValue = (int) Math.pow(10, place) * value;
            appendWithSpace(output, PREDEFINED_NUMBER_NAMES.get(currentPlaceValue));
        } else {
            // For valid hundredth place value, append 'hundred' to unit number
            appendWithSpace(output, PREDEFINED_NUMBER_NAMES.get(value));
            appendWithSpace(output, PLACE_VALUE_NAME_MAP.get(place));
        }
    }

    private static Map<Integer, String> buildPlaceValuesMap() {
        return ImmutableMap.<Integer, String>builder()
                .put(2, "hundred")
                .put(3, "thousand")
                .put(6, "million")
                .put(9, "billion")
                .put(12, "trillion")
                .put(15, "quadrillion")
                .put(18, "quintillion")
                .build();
    }

    private static Map<Integer, String> buildPredefinedNamingMap() {
        return ImmutableMap.<Integer, String>builder()
                .put(1, "one")
                .put(2, "two")
                .put(3, "three")
                .put(4, "four")
                .put(5, "five")
                .put(6, "six")
                .put(7, "seven")
                .put(8, "eight")
                .put(9, "nine")
                .put(10, "ten")
                .put(11, "eleven")
                .put(12, "twelve")
                .put(13, "thirteen")
                .put(14, "fourteen")
                .put(15, "fifteen")
                .put(16, "sixteen")
                .put(17, "seventeen")
                .put(18, "eighteen")
                .put(19, "nineteen")
                .put(20, "twenty")
                .put(30, "thirty")
                .put(40, "forty")
                .put(50, "fifty")
                .put(60, "sixty")
                .put(70, "seventy")
                .put(80, "eighty")
                .put(90, "ninety")
                .build();

    }
}
