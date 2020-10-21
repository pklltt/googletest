package com.automatedtest.sample.pages;

import com.automatedtest.sample.controls.DynamicControl;
import com.automatedtest.sample.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    DynamicControl dynamicControl = new DynamicControl(this.driverUtils,"//span[text()='%s']/parent::div");

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
        return value.equalsIgnoreCase("on");
    }

    public void saveSettingPage() {
        this.wait.forElementToBeDisplayed(this.buttonSave);
        this.buttonSave.click();
        this.wait.forLoading();
    }

    public void selectRadioSpoken(String value) {
        // get radio have text equal : value
        WebElement div = dynamicControl.findElement(value);
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
}
