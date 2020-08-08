package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.BasePage;
import io.cucumber.java.en.When;

public class CommonSteps {

    private final BasePage basePage;

    public CommonSteps() {
        this.basePage = new BasePage();
    }

    @When("I wait for (\\d*) seconds$")
    public void waitForVideoUntil(int seconds) {
        this.basePage.delay(seconds);
    }
}
