package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.ImageSearchResultPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ImageSearchResultSteps {

    private final ImageSearchResultPage imageSearchResultPage;

    public ImageSearchResultSteps() {
        this.imageSearchResultPage = new ImageSearchResultPage();
    }

    @Then("Image {string} should be uploaded on search page")
    public void imageShouldBeUploadedOnSearchPage(String filePath) {
        String imageName = this.imageSearchResultPage.getImageSearchName();
        Assert.assertTrue("filePath contains image name", filePath.contains(imageName));
    }

    @And("Search result page should have image result")
    public void searchResultPageShouldHaveImageResult() {
        Assert.assertTrue("searchResultPageShouldHaveImageResult: list size > 0", this.imageSearchResultPage.getListImageResult().size() > 0);
    }
}
