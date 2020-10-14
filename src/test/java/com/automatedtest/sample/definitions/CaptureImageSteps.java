package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.SettingPage;
import com.automatedtest.sample.utils.Log;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CaptureImageSteps {
    private final SettingPage settingPage;

    public CaptureImageSteps() {
        this.settingPage = new SettingPage();
    }

    @Given("I'm on Google Search Setting Page To Capture Image")
    public void aUserNavigateToSearchSettingPageToCaptureImage() {
        this.settingPage.goToSettingPage();
    }

    @When("I Scroll to bottom")
    public void aUserScrollToBottom() {
        this.settingPage.scrollToBottom();
    }

    @And("I Scroll to top")
    public void aUserScrollToTop() {
        this.settingPage.scrollToTop();
    }

    @And("I capture image {int}")
    public void aUserCaptureImage(int id) {
        try {
            this.settingPage.takeSnapShot(id);
        } catch (Exception e) {
            Assert.fail("User Capture Image Fail");
        }
    }

    @Then("Image1 and Image2 should be the same")
    public void imageAndImageShouldBeTheSame() {
        Assert.assertTrue("Image1 and Image2 should be the same",this.settingPage.compareImage());
    }
}
