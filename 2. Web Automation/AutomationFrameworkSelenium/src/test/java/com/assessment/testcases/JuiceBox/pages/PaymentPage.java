package com.assessment.testcases.JuiceBox.pages;

import com.assessment.constants.FrameworkConstants;
import com.assessment.testcases.JuiceBox.BasePageJB;
import com.assessment.utils.DataGenerateUtils;

import static com.assessment.keywords.WebUI.*;

import org.openqa.selenium.By;

public class PaymentPage extends BasePageJB {

    
    private By addNewCardSection  = By.id("mat-expansion-panel-header-0");
    private By nameTextbox  = By.xpath("(//mat-form-field)[2]//input");
    private By cardNumberTextbox  = By.xpath("(//mat-form-field)[3]//input");
    private By expiryMonthDD  = By.xpath("(//select)[1]");
    private By expiryYearDD  = By.xpath("(//select)[2]");
    private By submitButton   = By.id("submitButton");
    private By selectCard  = By.xpath("//mat-radio-button");
    
    private By proceedToReviewButton  = By.cssSelector("[aria-label='Proceed to review']");
    private By completePurchaseButton  = By.cssSelector("[aria-label='Complete your purchase']");
    private String cardNumber = "4323546576784566";



    
    public void addNewCard() {
        clickElement(addNewCardSection);
        sleep(3);
        selectOptionByIndex(expiryMonthDD, 3);
        selectOptionByIndex(expiryYearDD, 3);
        clearText(cardNumberTextbox);
        clickElement(cardNumberTextbox);
        setText(cardNumberTextbox, cardNumber);
        clearText(nameTextbox);
        clickElement(nameTextbox);
        setText(nameTextbox, DataGenerateUtils.randomFirstName());
        sleep(2); //for test stability
        clickElement(submitButton);

    }

        
    public void selectCardAndCompletePayment() {
        
        clickElementWithJs(selectCard);
        clickElement(proceedToReviewButton);
        clickElement(completePurchaseButton);
        


    }


}


