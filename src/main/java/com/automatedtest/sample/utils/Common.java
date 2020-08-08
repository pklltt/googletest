package com.automatedtest.sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
    public static String matchGroup(String text, String regex, int groupIdx) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(groupIdx);
        }
        return null;
    }
}
