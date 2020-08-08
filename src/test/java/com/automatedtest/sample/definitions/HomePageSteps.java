package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomePageSteps {


    private final HomePage homePage;

    public HomePageSteps() {
        this.homePage = new HomePage();
    }

    @Given("^I'm on Google home page$")
    public void aUserNavigatesToHomePage() {
        this.homePage.goToHomePage();
    }

    @When("^I search for \"([^\"]*)\"$")
    public void aUserSearchesFor(String searchValue) {
        this.homePage.searchFor(String.format("\"%s\"", searchValue));
    }
}
