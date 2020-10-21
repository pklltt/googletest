package com.automatedtest.sample.controls;

import com.automatedtest.sample.driver.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

//TODO: support generic dynamic element
public class DynamicControl {
    private String locator;

    public DynamicControl(String str) {
        locator = str;
    }

    private String format(Object... args) {
        return String.format(locator, args);
    }

    public WebElement findElement(Object... args){
        return Setup.driver.findElement(By.xpath(this.format(args)));
    }

    public List<WebElement> findElements(Object... args){
        return Setup.driver.findElements(By.xpath(this.format(args)));
    }

}
