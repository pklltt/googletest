package com.automatedtest.sample.utils;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

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
}
