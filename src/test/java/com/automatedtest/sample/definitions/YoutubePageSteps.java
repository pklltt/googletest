package com.automatedtest.sample.definitions;

import com.automatedtest.sample.pages.YoutubePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class YoutubePageSteps {

    private YoutubePage youtubePage;

    public YoutubePageSteps() {
        this.youtubePage = new YoutubePage();
    }

    @When("I wait until video reach \"([^\"]*)\" for (\\d*) seconds$")
    public void waitForVideoUntil(String time, double seconds) {
        this.youtubePage.waitUntilVideoTime(time, seconds);
    }

    @When("I click (start|pause) button on the video$")
    public void startOrPauseVideo(String action) {
        if (action.equals("start")) {
            this.youtubePage.startVideo();
        } else {
            this.youtubePage.pauseVideo();
        }
    }

    @Then("Video should be (playing|paused)$")
    public void canVideoBePlayed(String videoState) {
        boolean isPlaying = videoState.equals("playing");
        Assert.assertEquals(String.format("Video should be %s", videoState), isPlaying, this.youtubePage.isVideoPlaying());
    }
}
