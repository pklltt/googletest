package com.automatedtest.sample.pages;

import com.automatedtest.sample.utils.Constants;
import com.automatedtest.sample.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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

    public SettingPage() {
        PageFactory.initElements(this.driver, this);
    }

    public void goToSettingPage() {
        this.driver.get(SETTING_PAGE_URL);
        this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
    }

    public void clickChangeSafeSearch() {
        this.wait.forElementToBeDisplayed(this.divCheckSafeSearch);
        this.divCheckSafeSearch.click();
    }

    public void acceptConfirmDialog() {
        try {
            this.driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            // That's fine.
            Log.info("NoAlertPresentException :" + e);
        }
    }

    public String getSafeSearchValue(){
        this.wait.forElementToBeDisplayed(this.divCheckSafeSearch);
        String value = this.checkInput.getAttribute("value");
        if(value.equalsIgnoreCase("on"))
            return "on";
        return "off";
    }

    public void saveSettingPage(){
        this.wait.forElementToBeDisplayed(this.buttonSave);
        this.buttonSave.click();
    }

    public WebElement getDivRadioSpokenByValue(String value){
        try {
            String sxpath =  "//span[text()='" + value + "']/parent::div";
            WebElement div = this.driver.findElement(By.xpath(sxpath));
            return div;
        } catch (Exception e) {
            Log.info(e.toString());
            return null;
        }

    }

    public void selectRadioSpoken(String value){
        // get radio have text equal : value
        WebElement div = getDivRadioSpokenByValue(value);
        if(div == null) {
            Log.info("Not Found Radio with value = " + value);
            return;
        }

        this.wait.forElementToBeDisplayed(div);
        div.click();
    }

    public String getSelectedRadioSpokenValue(){
        List<WebElement> listDiv = this.driver.findElements(By.xpath("//div[@id='tts-radio']/child::div"));
        for(WebElement div : listDiv){
            if("true".equalsIgnoreCase(div.getAttribute("aria-checked"))) {
                WebElement span = div.findElement(By.xpath("span[text()]"));
                String value = span.getText();
                return value;
            }
        }
        return "";
    }
}
