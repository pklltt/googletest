package com.automatedtest.sample.pages;

import com.automatedtest.sample.driver.Action;
import com.automatedtest.sample.driver.Setup;
import com.automatedtest.sample.driver.Wait;
import com.automatedtest.sample.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected Wait wait;
    protected Action action;

    public BasePage() {
        this.driver = Setup.driver;
        this.wait = new Wait(this.driver);
        this.action = new Action(this.driver);
    }

    public void delay(int timeWait) {
        this.wait.sleep(timeWait);
    }

    /**
     * Check if all elements contain the given text
     *
     * @param webElements     Element list
     * @param text            input text
     * @param isCaseSensitive case sensitive or not
     * @return
     */
    public boolean doElementsContainText(List<WebElement> webElements, String text, boolean isCaseSensitive) {
        for (WebElement element : webElements) {
            String innerText = element.getAttribute("innerText");
            if (!isCaseSensitive) {
                innerText = innerText.toLowerCase();
                text = text.toLowerCase();
            }
            if (!innerText.contains(text)) {
                Log.info(String.format("%s text is not found in result content: %s", text, innerText));
                return false;
            }
        }
        return true;
    }
}
