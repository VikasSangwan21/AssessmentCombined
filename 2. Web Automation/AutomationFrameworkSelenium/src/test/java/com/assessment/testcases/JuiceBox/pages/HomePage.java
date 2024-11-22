package com.assessment.testcases.JuiceBox.pages;

import com.assessment.constants.FrameworkConstants;
import com.assessment.testcases.JuiceBox.BasePageJB;

import static com.assessment.keywords.WebUI.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePageJB {

    //Navigation Bar
    private By navBarAccount  = By.id("navbarAccount");
    private By navBarAccountLoginButton  = By.id("navbarLoginButton");

    private By itemsPerPageDropdown  = By.cssSelector("mat-select[aria-label='Items per page:']");
    private By dropdownOptions   = By.cssSelector("mat-option");
    private By displayedItems   = By.cssSelector("mat-grid-tile");
    private By displayedItemsAddToBasket   = By.cssSelector("[aria-label='Add to Basket']");
    private By paginationLabel   = By.cssSelector(".mat-paginator-range-label");
    private By popupItem   = By.cssSelector("div>.mat-dialog-container");
    private By popupItemImage   = By.xpath("//mat-dialog-container//img");
    private By popupItemReview   = By.xpath("//mat-dialog-container//mat-expansion-panel");
    private By closeButton   = By.xpath("//button[contains(@aria-label,'Close Dialog')]");
    private By welcomeMsgDismissButton   = By.xpath("//button[@aria-label='Close Welcome Banner']");
    private By loginPageHeader   = By.xpath("//h1[text()='Login']");
    private By addedToBasketMessage   = By.xpath("//snack-bar-container//span[contains(text(), 'Added')]");
    private By basketItemsCount   = By.xpath("//*[@aria-label='Show the shopping cart']/span/span[2]");
    private By navBarYourbasket   = By.cssSelector("[aria-label=\"Show the shopping cart\"]");
    private By BasketPageHeader   = By.xpath("//h1[text()=' Your Basket ']");

    public void openJBHomePageScrollToBottom() {
        openWebsite(FrameworkConstants.URL_JUICEBOX);
        clickElement(welcomeMsgDismissButton);
        scrollToPageBottom();
    }

    
    public void navigateToLoginPage() {
        openWebsite(FrameworkConstants.URL_JUICEBOX);
        clickElement(welcomeMsgDismissButton);
        clickElement(navBarAccount);
        clickElement(navBarAccountLoginButton);
        verifyElementVisible(loginPageHeader);
    }

    
    
    public void navigateToBasket() {
        clickElement(navBarYourbasket);
        verifyElementVisible(BasketPageHeader);
    }

        
    public void addProductsToBasket(int numberOfProducts) {
        List<WebElement> allItems  = getWebElements(displayedItemsAddToBasket);
        for(int i =0; i<numberOfProducts; i++){
            clickElement(allItems.get(i));
            //waitForElementVisible(addedToBasketMessage);
            //verifyElementVisible(addedToBasketMessage);
            sleep(5);
            verifyElementText(basketItemsCount, Integer.toString(i+1));
        }

    }


    public void verifyMaximumItemsPerPage() {
        clickElement(itemsPerPageDropdown);
        List<WebElement> numberOfItems  = getWebElements(dropdownOptions);
        clickElement(numberOfItems.get(numberOfItems.size() - 1));
        waitForPageLoaded();
        String[] temp = getTextElement(paginationLabel).split(" ");
        int expectedItemCount = Integer.parseInt(temp[temp.length - 1]);
        verifyEquals(getWebElements(displayedItems).size(), expectedItemCount, "All the Items are not displayed on the home page");
    }

    public void clickProductAndVerifyPopup(int productNumber) {
        openWebsite(FrameworkConstants.URL_JUICEBOX);
        clickElement(welcomeMsgDismissButton);
        List<WebElement> allItems  = getWebElements(displayedItems);
        clickElement(allItems.get(productNumber-1));
        waitForElementVisible(popupItem);
        verifyElementVisible(popupItem);
        // verify if product image is displayed in popup
        verifyElementVisible(popupItemImage);
        // verify if product review is displayed in popup
        verifyElementVisible(popupItemReview);
        // Click on review and verify
        clickElement(popupItemReview);
        sleep(2);
        // Close the Popup
        clickElement(closeButton);
    }

}


