package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.ImageSearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ImageSearchPageSteps {

    private final ImageSearchPage imageSearchPage;

    public ImageSearchPageSteps() {
        this.imageSearchPage = new ImageSearchPage();
    }

    @Given("I'm on Google Image Search page")
    public void aUserOnGoogleImageSearchPage() {
        this.imageSearchPage.goToImageSearchPage();
    }

    @When("I search for image with file path {string}")
    public void aUserSearchForImageWithFilePath(String filePath) {
        this.imageSearchPage.uploadImage(filePath);
    }

    @Then("Image {string} should be uploaded on search page")
    public void imageShouldBeUploadedOnSearchPage(String filePath) {
        String imageName = this.imageSearchPage.getImageSearchName();
        Assert.assertTrue("Image should be uploaded on search page", filePath.contains(imageName));
    }

    @And("Search result page should have image result")
    public void searchResultPageShouldHaveImageResult() {
        Assert.assertTrue("Search result page should have image result", this.imageSearchPage.getListImageResult().size() > 0);
    }
}
