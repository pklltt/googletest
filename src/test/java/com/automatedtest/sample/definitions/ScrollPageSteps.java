package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.SettingPage;
import com.automatedtest.sample.utils.Common;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ScrollPageSteps {
    private final SettingPage settingPage;

    public ScrollPageSteps() {
        this.settingPage = new SettingPage();
    }

    @When("I Scroll to bottom")
    public void aUserScrollToBottom() {
        this.settingPage.scrollToBottom();
    }

    @And("I Scroll to top")
    public void aUserScrollToTop() {
        this.settingPage.scrollToTop();
    }

    @And("I capture image and save it to {string}")
    public void aUserCaptureImage(String filePath) throws Exception {
        this.settingPage.takeSnapShot(filePath);
    }

    @Then("Image with file path {string} and {string} should be the same")
    public void TwoCaptureImageShouldBeTheSame(String image1, String image2) {
        Assert.assertTrue("Two capture image should be the same", Common.compareImage(image1, image2));
    }
}
