package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.ImageTabOfSearchResultPage;
import com.automatedtest.sample.pages.SearchResultPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class SearchImageByDropLink {
    private final SearchResultPage searchResultPage;
    private final ImageTabOfSearchResultPage imageTabOfSearchResultPage;


    public SearchImageByDropLink() {
        this.searchResultPage = new SearchResultPage();
        this.imageTabOfSearchResultPage = new ImageTabOfSearchResultPage();
    }

    @And("I change to image tab")
    public void aUserChangeToImageTab() {
        this.searchResultPage.changeToImageTab();
    }

    @When("I drag and drop first image result to search image")
    public void aUserDragAndDropFirstImageResultToSearchImage() {
        this.imageTabOfSearchResultPage.dragNDropFirstImageToSearch();
    }
}
