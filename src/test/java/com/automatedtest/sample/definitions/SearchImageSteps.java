package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.HomePage;
import com.automatedtest.sample.pages.SearchResultPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class SearchImageSteps {
    private final SearchResultPage searchResultPage;
    private final HomePage homePage;

    public SearchImageSteps() {
        this.searchResultPage = new SearchResultPage();
        this.homePage = new HomePage();
    }

    @When("I search image for {string}")
    public void aUserSearchImageWithString(String searchValue) {
        this.homePage.searchFor(String.format("\"%s\"", searchValue));
        this.searchResultPage.changeToImageTab();
    }

    @And("I drag and drop first image result to search image")
    public void aUserDragAndDropFirstImageResultToSearchImage() throws Exception {
        this.searchResultPage.dragNDropFirstImageToSearch();
    }
}
