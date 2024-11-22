package com.assessment.testcases.JuiceBox.pages;

import com.assessment.testcases.JuiceBox.BasePageJB;
import com.assessment.utils.DataGenerateUtils;

import static com.assessment.keywords.WebUI.*;

import org.openqa.selenium.By;

public class BasketPage extends BasePageJB {

    
    private By reduceQuantityButton  = By.xpath("(//*[@data-icon='minus-square'])[1]");
    private By increaseQuantityButton  = By.xpath("(//*[@data-icon='plus-square'])[1]");
    private By deleteProduct  = By.xpath("(//*[@data-icon='trash-alt'])[1]");
    private By totalPrice  = By.id("price");
    private By checkoutButton  = By.id("checkoutButton");
    private By addNewAddressButton  = By.cssSelector("[aria-label='Add a new address']");
    private By countryTextbox   = By.cssSelector("[data-placeholder='Please provide a country.']");
    private By nameTextbox   = By.cssSelector("[data-placeholder='Please provide a name.']");
    private By mobileTextbox   = By.cssSelector("[data-placeholder='Please provide a mobile number.']");
    private By zipTextbox   = By.cssSelector("[data-placeholder='Please provide a ZIP code.']");
    private By cityTextbox   = By.cssSelector("[data-placeholder='Please provide a city.']");
    private By stateTextbox   = By.cssSelector("[data-placeholder='Please provide a state.']");
    private By addressTextbox   = By.id("address");
    private By submitButton   = By.id("submitButton");
    private By selectAddress   = By.cssSelector("#mat-radio-42");
    private By oneDayDelivery   = By.xpath("//mat-row[1]");
    
    private By proceedToPaymentButton  = By.cssSelector("[aria-label='Proceed to payment selection']");
    private By proceedToDeliveryButton  = By.cssSelector("[aria-label='Proceed to delivery method selection']");
    private By paymentPageHeader  = By.xpath("//h1[text()='My Payment Options']");



    
    public void increaseItemQtyAndDeleteItem() {
        clickElement(increaseQuantityButton);
        String priceBeforeUpdate = getTextElement(totalPrice);
        clickElement(deleteProduct);
        String priceAfterUpdate = getTextElement(totalPrice);
        verifyNotEquals(priceBeforeUpdate,priceAfterUpdate, "Total Price is not updated");

    }

        
    public void checkoutToPaymentPage() {
        
        clickElement(checkoutButton);
        clickElement(addNewAddressButton);

        setText(countryTextbox, DataGenerateUtils.randomCountry());
        setText(nameTextbox, DataGenerateUtils.randomFullName());
       // setText(mobileTextbox, DataGenerateUtils.randomPhoneNumber());
        setText(mobileTextbox, "8888947373");
        setText(zipTextbox, "123456");
        setText(cityTextbox, DataGenerateUtils.randomCity());
        setText(stateTextbox, DataGenerateUtils.randomState());
        setText(addressTextbox, DataGenerateUtils.randomAddress());

        
        clickElement(submitButton);
        
        clickElement(selectAddress);
        clickElement(proceedToPaymentButton);
        clickElement(oneDayDelivery);
        clickElement(proceedToDeliveryButton);
        verifyElementVisible(paymentPageHeader);


    }


}


