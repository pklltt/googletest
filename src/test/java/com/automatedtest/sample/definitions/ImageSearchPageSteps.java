package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.ImageSearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ImageSearchPageSteps {

    private final ImageSearchPage imageSearchPage;

    public ImageSearchPageSteps() {
        this.imageSearchPage = new ImageSearchPage();
    }

    @Given("I'm on Google Image Search page")
    public void aUserOnGoogleImageSearchPage() {
        this.imageSearchPage.goToImageSearchPage();
    }

    @When("I search image for {string}")
    public void aUserSearchImageFor(String filePath) {
        this.imageSearchPage.uploadImage(filePath);
    }
}
