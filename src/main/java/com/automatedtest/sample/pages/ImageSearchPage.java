package com.automatedtest.sample.pages;

import com.automatedtest.sample.utils.Constants;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
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

    public void uploadImage(String filePath) {
        try {
            URL res = getClass().getClassLoader().getResource(filePath);
            File file = Paths.get(res.toURI()).toFile();
            String absolutePath = file.getAbsolutePath();
            this.wait.forElementToBeDisplayed(this.buttonChooseImage);
            this.buttonChooseImage.click();
            this.wait.forElementToBeDisplayed(this.linkTabUploadImage);
            this.linkTabUploadImage.click();
            this.wait.forElementToBeDisplayed(this.tabUploadImage);
            this.wait.forElementToBeDisplayed(this.buttonChooseFile);
            this.buttonChooseFile.sendKeys(absolutePath);
            this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
        } catch (Exception e) {
            Assert.fail("Upload image error");
        }
    }

    public String getImageSearchName() {
        this.wait.forElementToBeDisplayed(this.divSmallImageInSearch);
        return divSmallImageInSearch.getText();
    }

    public List<WebElement> getListImageResult() {
        return listImageResult;
    }


}
