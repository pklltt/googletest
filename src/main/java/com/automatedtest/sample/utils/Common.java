package com.automatedtest.sample.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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

    public static String getResourceAbsolutePath(Object object, String filePath) throws URISyntaxException {
        URL res = object.getClass().getClassLoader().getResource(filePath);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }
}
