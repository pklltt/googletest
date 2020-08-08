package com.automatedtest.sample.driver;

import com.automatedtest.sample.utils.Common;
import com.automatedtest.sample.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Wait {

    private WebDriver driver;

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    private String getLocator(WebElement element) {
        String elementString = element.toString();
        try {
            String proxyElement = Common.matchGroup(elementString, "'(By.*)'$", 1);
            return proxyElement == null
                    ? Common.matchGroup(elementString, "-> (.*)]", 1)
                    : proxyElement;
        } catch (Exception e) {
            System.out.println("Failed to get locator from: " + elementString);
            e.printStackTrace();
            return "[unknown]";
        }

    }

    private void waitUntilCondition(ExpectedCondition condition, String timeoutMessage, int timeout) {
        WebDriverWait wait = new WebDriverWait(this.driver, timeout);
        wait.withMessage(timeoutMessage);
        wait.until(condition);
    }

    public void forLoading() {
        forLoading(Constants.DEFAULT_TIME_WAIT);
    }

    public void forLoading(int timeout) {
        ExpectedCondition<Object> condition = ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
        String timeoutMessage = "Page didn't load after " + Integer.toString(timeout) + " seconds.";
        waitUntilCondition(condition, timeoutMessage, timeout);
    }

    public void forElementToBeDisplayed(WebElement webElement) {
        forElementToBeDisplayed(Constants.DEFAULT_TIME_WAIT, webElement);
    }

    public void forElementToBeDisplayed(int timeout, WebElement webElement) {
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(webElement);
        String timeoutMessage = String.format("Element with locator: %s wasn't displayed after %s seconds", getLocator(webElement), timeout);
        waitUntilCondition(condition, timeoutMessage, timeout);
    }

    public void forPresenceOfElements(By elementLocator) {
        forPresenceOfElements(Constants.DEFAULT_TIME_WAIT, elementLocator);
    }

    public void forPresenceOfElements(int timeout, By elementLocator) {
        ExpectedCondition<List<WebElement>> condition = ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator);
        String timeoutMessage = String.format("Element with locator: %s wasn't displayed after %s seconds", elementLocator.toString(), timeout);
        waitUntilCondition(condition, timeoutMessage, timeout);
    }

    public void forPresenceOfText(WebElement elementLocator, String text) {
        forPresenceOfText(Constants.DEFAULT_TIME_WAIT, elementLocator, text);
    }

    public void forPresenceOfText(int timeout, WebElement elementLocator, String text) {
        ExpectedCondition<Boolean> condition = ExpectedConditions.textToBePresentInElement(elementLocator, text);
        String timeoutMessage = String.format("%s text is not displayed in element with locator %s after %s seconds.",
                text, getLocator(elementLocator), timeout);
        try {
            waitUntilCondition(condition, timeoutMessage, timeout);
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
            System.out.printf("Expected: %s. Got: %s", text, elementLocator.getText());
        }

    }

    public void sleep(int timeWaitInSeconds) {
        try {
            Thread.sleep(timeWaitInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
