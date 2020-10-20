package com.automatedtest.sample.driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class DriverUtils {
    private WebDriver driver;
    private Wait wait;

    public DriverUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
    }

    public void scrollToBottom() {
        JavascriptExecutor js = ((JavascriptExecutor) this.driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        this.wait.forLoading();
    }

    public void scrollToTop() {
        JavascriptExecutor js = ((JavascriptExecutor) this.driver);
        js.executeScript("window.scrollTo(0, 0)");
        this.wait.forLoading();
    }

    public void takeSnapShot(String filePath) throws Exception {
        FileUtils.deleteQuietly(new File(filePath));
        TakesScreenshot scrShot = ((TakesScreenshot) this.driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(new File(SrcFile.getAbsolutePath()), new File(filePath));
    }
}
