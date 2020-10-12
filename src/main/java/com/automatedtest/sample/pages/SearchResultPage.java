package com.automatedtest.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//div[@class='rc' and .//span[@class='st' and text()]]")
    private List<WebElement> normalResults;

    @FindBy(xpath = "//g-section-with-header[.//h3[text()='Videos']]//a")
    private List<WebElement> videoResultUrls;

    @FindBy(xpath = "//g-section-with-header[.//h3[text()='Videos']]//a//div[@role='heading']")
    private List<WebElement> videoResultTitles;

    @FindBy(css = ".related-question-pair")
    private List<WebElement> questionResults;

    @FindBy(xpath = "//g-section-with-header[.//h3[text()='Top stories']]//a")
    private List<WebElement> topStoriesResults;

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchInput;

    @FindBy(xpath = "//a[text()='Images']")
    private WebElement linkImages;

    public SearchResultPage() {
        PageFactory.initElements(this.driver, this);
    }

    public String getSearchTextBox() {
        this.wait.forElementToBeDisplayed(this.searchInput);
        return this.searchInput.getAttribute("value");
    }

    public boolean doesSearchResultContainQuery(String queryText) {
        this.wait.forElementToBeDisplayed(this.normalResults.get(0));
        List<List<WebElement>> resultElementsList = new ArrayList<>(
                Arrays.asList(this.normalResults, this.videoResultUrls, this.questionResults, this.topStoriesResults));
        //verify test result on all the sections
        for (List<WebElement> resultElements : resultElementsList) {
            if (!doElementsContainText(resultElements, queryText, false)) {
                return false;
            }
        }
        return true;
    }

    public String openVideoAndGetName() {
        String videoName = this.videoResultTitles.get(0).getText();
        this.videoResultUrls.get(0).click();
        return videoName;
    }

    public void changeToImageTab() {
        this.wait.forElementToBeDisplayed(this.linkImages);
        this.linkImages.click();
    }
}

