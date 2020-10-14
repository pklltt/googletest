package com.automatedtest.sample.pages;

import com.automatedtest.sample.utils.Constants;
import com.automatedtest.sample.utils.Log;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.List;

public class SettingPage extends BasePage {
    private static final String SETTING_PAGE_URL = "https://www.google.com/preferences";

    @FindBy(xpath = "//span[text()='Turn on SafeSearch']/parent::div")
    private WebElement divCheckSafeSearch;

    @FindBy(xpath = "//span[text()='Turn on SafeSearch']/following::input[1]")
    private WebElement checkInput;

    @FindBy(xpath = "//div[text()='Save']")
    private WebElement buttonSave;

    @FindBy(xpath = "//div[@id='tts-radio']/child::div")
    private List<WebElement> listDiv;

    @FindBy(xpath = "//a[text()='Settings']")
    private WebElement linkSettings;

    @FindBy(xpath = "//a[text()='Search settings']")
    private WebElement linkSearchSettings;

    private String imagePath1 = "target/capture-image-html/screenshot1.png";
    private String imagePath2 = "target/capture-image-html/screenshot2.png";
    private String compareImagePath = "target/capture-image-html/compare.png";

    public SettingPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void goToSettingPage() {
        this.driver.get(SETTING_PAGE_URL);
        this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
    }

    public void goToSettingPageFromSearchPage() {
        this.wait.forElementToBeDisplayed(this.linkSettings);
        this.linkSettings.click();
        this.wait.forElementToBeDisplayed(this.linkSearchSettings);
        this.linkSearchSettings.click();
        this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
    }

    public void clickChangeSafeSearch() {
        this.wait.forElementToBeDisplayed(this.divCheckSafeSearch);
        this.divCheckSafeSearch.click();
    }

    public boolean getSafeSearchState() {
        this.wait.forElementToBeDisplayed(this.divCheckSafeSearch);
        String value = this.checkInput.getAttribute("value");
        if (value.equalsIgnoreCase("on"))
            return true;
        return false;
    }

    public void saveSettingPage() {
        this.wait.forElementToBeDisplayed(this.buttonSave);
        this.buttonSave.click();
    }

    //TODO: support generic dynamic element
    public WebElement getDivRadioSpokenByValue(String value) {
        try {
            String sxpath = "//span[text()='" + value + "']/parent::div";
            WebElement div = this.driver.findElement(By.xpath(sxpath));
            return div;
        } catch (Exception e) {
            Log.info(e.toString());
            return null;
        }
    }

    public void selectRadioSpoken(String value) throws Exception {
        // get radio have text equal : value
        WebElement div = getDivRadioSpokenByValue(value);
        if (div == null) {
            Log.info("Not Found Radio with value = " + value);
            throw new Exception("Not Found Radio with value = " + value);
        }

        this.wait.forElementToBeDisplayed(div);
        div.click();
    }

    public String getSelectedRadioSpokenValue() {
        String value = "";
        for (WebElement div : listDiv) {
            value = div.findElement(By.xpath("span[text()]")).getText();
            if ("true".equalsIgnoreCase(div.getAttribute("aria-checked"))) {
                break;
            }
        }
        return value;
    }

    public void scrollToBottom() {
        JavascriptExecutor js = ((JavascriptExecutor) this.driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        this.wait.sleep(1);
    }

    public void scrollToTop() {
        JavascriptExecutor js = ((JavascriptExecutor) this.driver);
        js.executeScript("window.scrollTo(0, 0)");
        this.wait.sleep(1);
    }

    public void takeSnapShot(int id) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) this.driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        if (id == 1) {
            FileUtils.copyFile(new File(SrcFile.getAbsolutePath()), new File(imagePath1));
        }

        if (id == 2) {
            FileUtils.copyFile(new File(SrcFile.getAbsolutePath()), new File(imagePath2));
        }
    }

    public boolean compareImage() {
        ImageComparison imageComparison = new ImageComparison(imagePath1, imagePath2);
        imageComparison.setDestination(new File(compareImagePath));
        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
        ImageComparisonState imageComparisonState = imageComparisonResult.getImageComparisonState();

        return (imageComparisonState == ImageComparisonState.MATCH);
    }
}
