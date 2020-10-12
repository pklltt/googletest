package com.automatedtest.sample.pages;

import com.automatedtest.sample.utils.Constants;
import org.junit.Assert;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.List;

public class ImageTabOfSearchResultPage extends BasePage  {
    @FindBy(xpath = "//input[@value='Search by image']")
    WebElement buttonSearchByImage;

    @FindBy(xpath = "//span[@aria-label='Search by image']")
    WebElement spanSearchByImage;

    @FindBy(xpath = "//img[starts-with(@src,'data:image') and @alt!='']/parent::div")
    List<WebElement> listImageResult;

    @FindBy(xpath = "//div[text()='Search by image']/parent::div/parent::div")
    WebElement divToDropURL;

    @FindBy(xpath = "//span[text()='Paste image URL']")
    WebElement tabPasteURL;

    public ImageTabOfSearchResultPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void dragNDropFirstImageToSearch() {
        this.wait.forElementToBeDisplayed(this.spanSearchByImage);
        this.spanSearchByImage.click();
        this.wait.forElementToBeDisplayed(this.tabPasteURL);
        this.wait.forElementToBeDisplayed(this.buttonSearchByImage);

        try {
            dragAndDropElement(listImageResult.get(0), divToDropURL);
        } catch (Exception e) {
            Assert.fail("dragNDropFirstImageToSearch False");
        }
        this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
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
