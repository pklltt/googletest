package com.automatedtest.sample.controls;

import com.automatedtest.sample.driver.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

//TODO: support generic dynamic element
public class DynamicControl {
    private String locator;
    private DriverUtils driverUtils;

    public DynamicControl(DriverUtils driverUtils, String str) {
        locator = str;
        this.driverUtils = driverUtils;
    }

    private String format(Object... args) {
        return String.format(locator, args);
    }

    public WebElement findElement(Object... args) {
        return this.driverUtils.getDriver().findElement(By.xpath(this.format(args)));
    }

    public List<WebElement> findElements(Object... args) {
        return this.driverUtils.getDriver().findElements(By.xpath(this.format(args)));
    }

}
