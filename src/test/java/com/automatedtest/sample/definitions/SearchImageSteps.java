package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.SearchResultPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class SearchImageSteps {
    private final SearchResultPage searchResultPage;

    public SearchImageSteps() {
        this.searchResultPage = new SearchResultPage();
    }

    @And("I change to image tab")
    public void aUserChangeToImageTab() {
        this.searchResultPage.changeToImageTab();
    }

    @When("I drag and drop first image result to search image")
    public void aUserDragAndDropFirstImageResultToSearchImage() throws Exception {
        this.searchResultPage.dragNDropFirstImageToSearch();
    }
}
