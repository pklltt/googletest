package com.automatedtest.sample.driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class DriverUtils {
    public static ThreadLocal<WebDriver> currentDriver = new ThreadLocal<WebDriver>();
    private Wait wait;

    public DriverUtils() {
        this.wait = new Wait(this.currentDriver.get());
    }

    public static void driverInit() {
        WebDriver driver;

        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("['start-maximized']");
                chromeOptions.addArguments("--lang=en-GB");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages","en-GB");
                FirefoxOptions options = new FirefoxOptions();
                options.setProfile(profile);
                driver = new FirefoxDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
        driver.manage().window().maximize();
        currentDriver.set(driver);
    }

    public void scrollToBottom() {
        JavascriptExecutor js = ((JavascriptExecutor) this.getDriver());
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        this.wait.forLoading();
    }

    public void scrollToTop() {
        JavascriptExecutor js = ((JavascriptExecutor) this.getDriver());
        js.executeScript("window.scrollTo(0, 0)");
        this.wait.forLoading();
    }

    public void takeSnapShot(String filePath) throws Exception {
        FileUtils.deleteQuietly(new File(filePath));
        TakesScreenshot scrShot = ((TakesScreenshot) this.getDriver());
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(new File(SrcFile.getAbsolutePath()), new File(filePath));
    }

    public static WebDriver getDriver(){
        return currentDriver.get();
    }
}
