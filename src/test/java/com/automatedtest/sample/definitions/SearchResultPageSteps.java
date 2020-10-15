package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.ImageSearchPage;
import com.automatedtest.sample.pages.SearchResultPage;
import com.automatedtest.sample.pages.YoutubePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.net.URISyntaxException;

public class SearchResultPageSteps {

    private final SearchResultPage searchResultPage;
    private final YoutubePage youtubePage;
    private final ImageSearchPage imageSearchPage;

    public SearchResultPageSteps() {
        this.searchResultPage = new SearchResultPage();
        this.youtubePage = new YoutubePage();
        this.imageSearchPage = new ImageSearchPage();
    }

    @Then("^\"([^\"]*)\" is displayed in all the results$")
    public void isSearchResultDisplayedCorrectly(String queryText) {
        Assert.assertTrue("Search result should be displayed correctly",
                this.searchResultPage.doesSearchResultContainQuery(queryText));
    }

    @Then("^\"([^\"]*)\" is till remaining in the search box$")
    public void isSearchTextBoxDisplayedCorrectly(String queryText) {
        Assert.assertEquals("Query text should remaining in the search box",
                queryText, this.searchResultPage.getSearchTextBox().replace("\"", ""));
    }

    @Then("First video of Google search result can be opened correctly$")
    public void canFirstVideoBeOpened() {
        String videoURL = this.searchResultPage.openFirstVideoAndGetUrl();
        String youtubeVideoURL = this.youtubePage.getCurrentUrl();

        Assert.assertEquals("First video of Google search result should be opened correctly",
                videoURL, youtubeVideoURL);
    }

    @Given("I'm on Google Image Search page")
    public void aUserOnGoogleImageSearchPage() {
        this.imageSearchPage.goToImageSearchPage();
    }

    @When("I search for image with file path {string}")
    public void aUserSearchForImageWithFilePath(String filePath) throws URISyntaxException {
        this.imageSearchPage.uploadImage(filePath);
    }

    @Then("Image with file path {string} should be uploaded on search page")
    public void imageShouldBeUploadedOnSearchPage(String filePath) {
        String imageName = this.imageSearchPage.getImageSearchName();
        Assert.assertTrue("Image should be uploaded on search page", filePath.contains(imageName));
    }

    @And("Search result page should have image result")
    public void searchResultPageShouldHaveImageResult() {
        Assert.assertTrue("Search result page should have image result", this.imageSearchPage.getListImageResult().size() > 0);
    }
}
