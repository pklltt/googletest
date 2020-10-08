package com.automatedtest.sample.definitions;

import com.automatedtest.sample.driver.Wait;
import com.automatedtest.sample.pages.HomePage;
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

    @When("I Check Turn {string} SafeSearch")
    public void aUserCheckTurnOnOffSafeSearch(String onOff) {
        if(!onOff.equalsIgnoreCase(this.settingPage.getSafeSearchValue()))
            this.settingPage.clickChangeSafeSearch();
    }

    @And("I save the Setting Page")
    public void aUserSaveTheSettingPage() {
        this.settingPage.saveSettingPage();
        this.settingPage.acceptConfirmDialog();
        this.settingPage.delay(1);
    }

    @And("I comeback Setting Page again")
    public void aUserComebackSettingPageAgain() {
        this.settingPage.goToSettingPage();
    }


    @Then("SafeSearch  check status should be {string}")
    public void safeSearchCheckStatusShouldBe(String status) {
        Assert.assertEquals("SafeSearch Should be on Checked",status,this.settingPage.getSafeSearchValue());
    }

    @And("I select radio button {string} Spoken answers")
    public void aUserSelectRadioButtonSpokenAnswers(String option) {
        this.settingPage.selectRadioSpoken(option);
    }

    @Then("Spoken answers radio selected should be {string}")
    public void spokenAnswersRadioSelectedShouldBe(String option) {
        Assert.assertEquals("spoken Answers Radio Selected Should Be Correct",option, this.settingPage.getSelectedRadioSpokenValue());
    }
}
