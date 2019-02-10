package com.naresh.sonatype.utils;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StringUtilsTest {

    @Test
    public void titleCase_whenNullOrEmpty_returnsEmpty() {
        assertThat(StringUtils.titleCase(""), is(""));
        assertThat(StringUtils.titleCase(null), is(""));
    }

    @Test
    public void titleCase_whenValidInput_convertsFirstCharToUpperCase() {
        assertThat(StringUtils.titleCase("test input"), is("Test input"));
    }

    @Test
    public void appendWithSpace_whenValidInput_appendsWithSpace() {
        StringBuilder output = new StringBuilder("prefix");
        StringUtils.appendWithSpace(output, "suffix");
        assertThat(output.toString(), is("prefix suffix"));
    }

    @Test
    public void appendWithSpace_whenEmptyPrefix_appendsSuffix() {
        StringBuilder output = new StringBuilder();
        StringUtils.appendWithSpace(output, "suffix");
        assertThat(output.toString(), is("suffix"));
    }

    @Test
    public void appendWithSpace_whenEmptySuffix_returnsUnChanged() {
        StringBuilder output = new StringBuilder("prefix");
        StringUtils.appendWithSpace(output, "");
        assertThat(output.toString(), is("prefix"));
    }
}