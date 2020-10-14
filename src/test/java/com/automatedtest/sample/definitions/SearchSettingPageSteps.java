package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.SettingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SearchSettingPageSteps {

    private final SettingPage settingPage;

    public SearchSettingPageSteps() {
        this.settingPage = new SettingPage();
    }

    @Given("I'm on Google Search Setting Page")
    public void aUserNavigatesToSearchSettingPage() {
        this.settingPage.goToSettingPage();
    }

    @When("^I Check Turn (\"on\"|\"off\") SafeSearch$")
    public void aUserCheckTurnOnOffSafeSearch(String state) {
        boolean bState = "\"on\"".equalsIgnoreCase(state);
        if (this.settingPage.getSafeSearchState() != bState)
            this.settingPage.clickChangeSafeSearch();
    }

    @And("I save the Setting Page")
    public void aUserSaveTheSettingPage() {
        this.settingPage.saveSettingPage();
        this.settingPage.delay(2);
    }

    @And("I comeback Setting Page again")
    public void aUserComebackSettingPageAgain() {
        this.settingPage.goToSettingPageFromSearchPage();
    }

    @Then("SafeSearch  check status should be {string}")
    public void safeSearchCheckStatusShouldBe(String status) {
        Assert.assertEquals("SafeSearch Should be on Checked", status, this.settingPage.getSafeSearchState() ? "on" : "off");
    }

    @And("I select radio button {string} Spoken answers")
    public void aUserSelectRadioButtonSpokenAnswers(String option) {
        try {
            this.settingPage.selectRadioSpoken(option);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("Spoken answers radio selected should be {string}")
    public void spokenAnswersRadioSelectedShouldBe(String option) {
        Assert.assertEquals("spoken Answers Radio Selected Should Be Correct", option, this.settingPage.getSelectedRadioSpokenValue());
    }
}
