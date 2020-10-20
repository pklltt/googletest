package com.automatedtest.sample.driver;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.InputEvent;

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

    public void dragAndDropElement(WebElement dragFrom, WebElement dragTo) throws Exception {
        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);
        // Get size of elements
        org.openqa.selenium.Dimension fromSize = dragFrom.getSize();
        org.openqa.selenium.Dimension toSize = dragTo.getSize();
        org.openqa.selenium.Point toLocation = dragTo.getLocation();
        Point fromLocation = dragFrom.getLocation();
        //Make Mouse coordinate centre of element
        toLocation.x += toSize.width / 2;
        toLocation.y += toSize.height / 2 + 50;
        fromLocation.x += fromSize.width / 2;
        fromLocation.y += fromSize.height / 2 + 50;

        //Move mouse to drag from location
        robot.mouseMove(fromLocation.x, fromLocation.y);
        //Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);

        //Drag events require more than one movement to register
        //Just appearing at destination doesn't work so move halfway first
        robot.mouseMove(((toLocation.x - fromLocation.x) / 2) + fromLocation.x, ((toLocation.y - fromLocation.y) / 2) + fromLocation.y);

        //Move to final position
        robot.mouseMove(toLocation.x, toLocation.y);
        //Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
