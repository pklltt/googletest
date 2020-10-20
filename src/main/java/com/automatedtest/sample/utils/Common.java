package com.automatedtest.sample.utils;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

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

    public static boolean compareImage(String imagePath1, String imagePath2) {
        ImageComparison imageComparison = new ImageComparison(imagePath1, imagePath2);
        //imageComparison.setDestination(new File(compareImagePath));
        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
        ImageComparisonState imageComparisonState = imageComparisonResult.getImageComparisonState();

        return (imageComparisonState == ImageComparisonState.MATCH);
    }

    public static String getResourceAbsolutePath(Object object, String filePath) throws URISyntaxException {
        URL res = object.getClass().getClassLoader().getResource(filePath);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }
}
