package com.automatedtest.sample.pages;

import com.automatedtest.sample.utils.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImageSearchPage extends BasePage {

    private static final String HOME_PAGE_URL = "https://www.google.com/imghp?hl=en&tab=wi&ogbl";

    @FindBy (xpath = "//div[@aria-label='Search by image']")
    WebElement buttonChooseImage;

    @FindBy (xpath = "//a[text()='Upload an image']")
    WebElement linkTabUploadImage;

    @FindBy (xpath = "//span[text()='Upload an image']")
    WebElement tabUploadImage;

    @FindBy (xpath = "//input[@name='encoded_image']")
    WebElement buttonChooseFile;

    public ImageSearchPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void goToImageSearchPage() {
        this.driver.get(HOME_PAGE_URL);
        this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
    }

    public void uploadImage(String filePath){
        this.wait.forElementToBeDisplayed(this.buttonChooseImage);
        this.buttonChooseImage.click();
        this.wait.forElementToBeDisplayed(this.linkTabUploadImage);
        this.linkTabUploadImage.click();
        this.wait.forElementToBeDisplayed(this.tabUploadImage);
        this.wait.forElementToBeDisplayed(this.buttonChooseFile);
        this.buttonChooseFile.sendKeys(filePath);
        this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
    }
}
