package com.naresh.learning.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NumberToWordsTest {

    @Test
    public void transformBelowThousand_when_0_returns_Zero() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformBelowThousand(0, 0, result);
        assertThat(result.toString(), is("Zero"));
    }

    @Test
    public void transformBelowThousand_when_singleDigit_returns_successfully() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformBelowThousand(6, 0, result);
        assertThat(result.toString(), is("six"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(9, 0, result);
        assertThat(result.toString(), is("nine"));
    }

    @Test
    public void transformBelowThousand_when_predefined_doubleDigit_returns_successfully() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformBelowThousand(16, 0, result);
        assertThat(result.toString(), is("sixteen"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(30, 0, result);
        assertThat(result.toString(), is("thirty"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(11, 0, result);
        assertThat(result.toString(), is("eleven"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(10, 0, result);
        assertThat(result.toString(), is("ten"));
    }

    @Test
    public void transformBelowThousand_when_NonPredefined_doubleDigit_returns_successfully() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformBelowThousand(22, 0, result);
        assertThat(result.toString(), is("twenty two"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(37, 0, result);
        assertThat(result.toString(), is("thirty seven"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(53, 0, result);
        assertThat(result.toString(), is("fifty three"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(99, 0, result);
        assertThat(result.toString(), is("ninety nine"));
    }

    @Test
    public void transformBelowThousand_when_divisibleByHundred_returns_withoutAnd() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformBelowThousand(100, 0, result);
        assertThat(result.toString(), is("one hundred"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(300, 0, result);
        assertThat(result.toString(), is("three hundred"));
    }

    @Test
    public void transformBelowThousand_when_notDivisibleHundred_returns_withAnd() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformBelowThousand(111, 0, result);
        assertThat(result.toString(), is("one hundred and eleven"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(450, 0, result);
        assertThat(result.toString(), is("four hundred and fifty"));


        result = new StringBuilder();
        NumberToWords.transformBelowThousand(223, 0, result);
        assertThat(result.toString(), is("two hundred and twenty three"));
    }

    @Test
    public void transformBelowThousand_when_zeroInTensPlace_returns_withAnd() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformBelowThousand(101, 0, result);
        assertThat(result.toString(), is("one hundred and one"));

        result = new StringBuilder();
        NumberToWords.transformBelowThousand(306, 0, result);
        assertThat(result.toString(), is("three hundred and six"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformBelowThousand_when_aboveThousand_throwsException() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformBelowThousand(1232, 0, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void transformBelowThousand_when_thousand_throwsException() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformBelowThousand(1000, 0, result);
    }

    @Test
    public void transformThousandAndAbove_when_divisibleByThousands_returns_successfully() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(1000L, 0, result);
        assertThat(result.toString(), is("one thousand"));

        result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(6000L, 0, result);
        assertThat(result.toString(), is("six thousand"));

        result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(85000L, 0, result);
        assertThat(result.toString(), is("eighty five thousand"));
    }

    @Test
    public void transformThousandAndAbove_when_divisibleByHundred_returns_successfully() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(1300L, 0, result);
        assertThat(result.toString(), is("one thousand three hundred"));

        result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(6200L, 0, result);
        assertThat(result.toString(), is("six thousand two hundred"));

        result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(185700L, 0, result);
        assertThat(result.toString(), is("one hundred and eighty five thousand seven hundred"));
    }

    @Test
    public void transformThousandAndAbove_when_valuesInTensPlace_returns_successfully() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(1311L, 0, result);
        assertThat(result.toString(), is("one thousand three hundred and eleven"));

        result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(74346L, 0, result);
        assertThat(result.toString(), is("seventy four thousand three hundred and forty six"));

        result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(185750L, 0, result);
        assertThat(result.toString(), is("one hundred and eighty five thousand seven hundred and fifty"));
    }

    @Test
    public void transformThousandAndAbove_when_powersOfTen_returns_successfully() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(1000L, 0, result);
        assertThat(result.toString(), is("one thousand"));

        result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(10000L, 0, result);
        assertThat(result.toString(), is("ten thousand"));

        result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(1000000L, 0, result);
        assertThat(result.toString(), is("one million"));

        result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(1000000000000L, 0, result);
        assertThat(result.toString(), is("one trillion"));
    }

    @Test
    public void transformThousandAndAbove_when_MaxValue_returns_successfully() {
        StringBuilder result = new StringBuilder();
        NumberToWords.transformThousandAndAbove(Long.MAX_VALUE, 0, result);
        assertThat(result.toString(),
                is("nine quintillion two hundred and twenty three quadrillion three hundred and seventy two trillion and thirty six billion eight hundred and fifty four million seven hundred and seventy five thousand eight hundred and seven"));
    }

    @Test
    public void transform_when_MinValue_returns_withNegative_inTitleCase() {
        assertThat(NumberToWords.transform(Long.MIN_VALUE),
                is("Negative nine quintillion two hundred and twenty three quadrillion three hundred and seventy two trillion and thirty six billion eight hundred and fifty four million seven hundred and seventy five thousand eight hundred and eight"));
    }

    @Test
    public void transform_whenAboveThousand_positiveNumbers_returnsInTitleCase() {
        assertThat(NumberToWords.transform(123464L), is("One hundred and twenty three thousand four hundred and sixty four"));

        assertThat(NumberToWords.transform(56000000000L), is("Fifty six billion"));
    }

    @Test
    public void transform_whenBelowThousand_positiveNumbers_returnsInTitleCase() {
        assertThat(NumberToWords.transform(289L), is("Two hundred and eighty nine"));

        assertThat(NumberToWords.transform(918L), is("Nine hundred and eighteen"));

        assertThat(NumberToWords.transform(704L), is("Seven hundred and four"));

        assertThat(NumberToWords.transform(0L), is("Zero"));
    }

    @Test
    public void transform_whenAboveThousand_negativeNumbers_returnsWithNegative_inTitleCase() {
        assertThat(NumberToWords.transform(-123464L), is("Negative one hundred and twenty three thousand four hundred and sixty four"));

        assertThat(NumberToWords.transform(-56000000000L), is("Negative fifty six billion"));
    }

    @Test
    public void transform_whenBelowThousand_negativeNumbers_returnsWithNegative_inTitleCase() {
        assertThat(NumberToWords.transform(-289L), is("Negative two hundred and eighty nine"));

        assertThat(NumberToWords.transform(-918L), is("Negative nine hundred and eighteen"));

        assertThat(NumberToWords.transform(-704L), is("Negative seven hundred and four"));

        assertThat(NumberToWords.transform(-3L), is("Negative three"));
    }
}