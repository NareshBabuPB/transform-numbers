package com.naresh.learning.dto;

public class ConversionResponse {
    private String numberInWords;

    public ConversionResponse(String numberInWords) {
        this.numberInWords = numberInWords;
    }

    public ConversionResponse() {

    }

    public String getNumberInWords() {
        return this.numberInWords;
    }

    public void setNumberInWords(String numberInWords) {
        this.numberInWords = numberInWords;
    }
}
