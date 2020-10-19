package com.automatedtest.sample.utils;

//TODO: support generic dynamic element
public class Link {
    private String locator;

    public Link(String str) {
        locator = str;
    }

    public String format(Object... args) {
        return String.format(locator, args);
    }
}
