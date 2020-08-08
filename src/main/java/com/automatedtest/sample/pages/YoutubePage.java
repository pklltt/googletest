package com.automatedtest.sample.pages;

import com.automatedtest.sample.utils.Common;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;


public class YoutubePage extends BasePage {
    private static final String PLAY_BUTTON_TITLE = "Play (k)";
    private static final String PAUSE_BUTTON_TITLE = "Pause (k)";

    @FindBy(css = ".ytp-time-current")
    private WebElement currentTime;

    @FindBy(css = ".ytp-settings-button")
    private WebElement settingsButton;

    @FindBy(css = ".ytp-play-button")
    private WebElement playButton;

    @FindBy(css = "video")
    private WebElement video;

    @FindBy(css = ".ytp-play-progress")
    private WebElement playProgress;

    @FindBy(xpath = "//h1[contains(@class, 'title')]/yt-formatted-string")
    private WebElement videoTitle;


    public YoutubePage() {
        PageFactory.initElements(this.driver, this);
    }

    public void waitUntilVideoTime(String time, double timeOut) {
        this.wait.forElementToBeDisplayed(this.currentTime);
        StopWatch sw = new StopWatch();
        sw.start();
        while (sw.getTime() < timeOut * 1000) {
            this.wait.forPresenceOfText(this.currentTime, time);
        }
        sw.stop();
    }


    public void startVideo() {
        this.wait.forElementToBeDisplayed(this.video);
        //for some reason if we hover on the Play button, then the click on it won't work, so I use the setting button
        this.action.moveTo(this.settingsButton);
        if (this.playButton.getAttribute("title").equals(PLAY_BUTTON_TITLE))
            this.playButton.click();
    }


    public void pauseVideo() {
        this.action.moveTo(this.settingsButton);
        if (this.playButton.getAttribute("title").equals(PAUSE_BUTTON_TITLE)) {
            this.playButton.click();
        }

    }

    public double getElementScaleX(WebElement element) {
        String scaleXRegex = "scaleX\\(([\\d.]*)\\)";
        return Double.parseDouble(
                Objects.requireNonNull(Common.matchGroup(element.getAttribute("style"), scaleXRegex, 1)));
    }

    public boolean isVideoPlaying() {
        double videoProcessState = getElementScaleX(this.playProgress);
        this.wait.sleep(2);
        //the scaleX attribute will be increased if the video is playing
        return videoProcessState < getElementScaleX(this.playProgress);
    }

    public String getVideoTitle() {
        this.wait.forElementToBeDisplayed(this.videoTitle);
        return this.videoTitle.getText();
    }
}
