package com.automatedtest.sample.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Action {

    private Actions action;

    public Action(WebDriver webDriver) {
        this.action = new Actions(webDriver);
    }

    public void moveTo(WebElement element) {
        this.action.moveToElement(element).build().perform();
    }

    public void moveToAndClick(WebElement element) {
        this.action.moveToElement(element).click().build().perform();
    }

}
