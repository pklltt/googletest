package com.automatedtest.sample.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ImageSearchResultPage extends BasePage{

    @FindBy (xpath = "//div[@class='chip_text']")
    WebElement divSmallImageInSearch;

    @FindBy (xpath = "//g-section-with-header//img[@alt='Image result']")
    List<WebElement> listImageResult;

    public ImageSearchResultPage() {
        PageFactory.initElements(this.driver, this);
    }

    public String getImageSearchName(){
        this.wait.forElementToBeDisplayed(this.divSmallImageInSearch);
        return divSmallImageInSearch.getText();
    }

    public List<WebElement> getListImageResult(){
        return listImageResult;
    }
}
