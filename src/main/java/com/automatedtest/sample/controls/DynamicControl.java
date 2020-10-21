package com.automatedtest.sample.controls;

import com.automatedtest.sample.driver.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DynamicControl {
    private String locator;

    public DynamicControl(String str) {
        locator = str;
    }

    private String format(Object... args) {
        return String.format(locator, args);
    }

    public WebElement findElement(Object... args) {
        return DriverUtils.getDriver().findElement(By.xpath(this.format(args)));
    }

    public List<WebElement> findElements(Object... args) {
        return DriverUtils.getDriver().findElements(By.xpath(this.format(args)));
    }
}
