package com.automatedtest.sample.pages;

import com.automatedtest.sample.utils.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

    private static final String HOME_PAGE_URL = "https://www.google.com";

    @FindBy(css = "#hplogo")
    private WebElement logo;

    @FindBy(css = "input[name=q]")
    private WebElement searchInput;

    @FindBy(xpath = "//input[@name='btnK' and @value='Google Search']")
    private WebElement searchButton;


    public HomePage() {
        PageFactory.initElements(this.driver, this);
    }

    public void goToHomePage() {
        this.driver.get(HOME_PAGE_URL);
        this.wait.forLoading(Constants.DEFAULT_TIME_WAIT);
    }

    public void searchFor(String searchValue) {
        this.searchInput.sendKeys(searchValue);
        this.wait.forElementToBeDisplayed(this.searchButton);
        this.searchButton.click();
    }
}
