package com.automatedtest.sample.pages;

import com.automatedtest.sample.utils.Common;
import com.automatedtest.sample.utils.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.URISyntaxException;
import java.util.List;

public class ImageSearchPage extends BasePage {

    private static final String HOME_PAGE_URL = "https://www.google.com/imghp?hl=en&tab=wi&ogbl";

    @FindBy(xpath = "//div[@aria-label='Search by image']")
    WebElement buttonChooseImage;

    @FindBy(xpath = "//a[text()='Upload an image']")
    WebElement linkTabUploadImage;

    @FindBy(xpath = "//span[text()='Upload an image']")
    WebElement tabUploadImage;

    @FindBy(xpath = "//input[@name='encoded_image']")
    WebElement buttonChooseFile;

    @FindBy(xpath = "//div[@class='chip_text']")
    WebElement divSmallImageInSearch;

    @FindBy(xpath = "//g-section-with-header//img[@alt='Image result']")
    List<WebElement> listImageResult;


    public ImageSearchPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void goToImageSearchPage() {
        this.driver.get(HOME_PAGE_URL);
        this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
    }

    public void uploadImage(String filePath) throws URISyntaxException {
        this.wait.forElementToBeDisplayed(this.buttonChooseImage);
        this.buttonChooseImage.click();
        this.wait.forElementToBeDisplayed(this.linkTabUploadImage);
        this.linkTabUploadImage.click();
        this.wait.forElementToBeDisplayed(this.tabUploadImage);
        this.wait.forElementToBeDisplayed(this.buttonChooseFile);
        this.buttonChooseFile.sendKeys(Common.getResourceAbsolutePath(this, filePath));
        this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
    }

    public String getImageSearchName() {
        this.wait.forElementToBeDisplayed(this.divSmallImageInSearch);
        return divSmallImageInSearch.getText();
    }

    public List<WebElement> getListImageResult() {
        return listImageResult;
    }
}
