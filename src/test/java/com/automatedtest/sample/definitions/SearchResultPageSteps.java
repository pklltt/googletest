package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.SearchResultPage;
import com.automatedtest.sample.pages.YoutubePage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class SearchResultPageSteps {

    private final SearchResultPage searchResultPage;
    private final YoutubePage youtubePage;

    public SearchResultPageSteps() {
        this.searchResultPage = new SearchResultPage();
        this.youtubePage = new YoutubePage();
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
        String videoName = this.searchResultPage.openVideoAndGetName();
        Assert.assertEquals("First video of Google search result should be opened correctly",
                videoName, this.youtubePage.getVideoTitle());
    }
}
