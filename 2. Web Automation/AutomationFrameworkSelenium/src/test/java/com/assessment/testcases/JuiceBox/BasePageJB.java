package com.assessment.testcases.JuiceBox;

import static com.assessment.keywords.WebUI.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.assessment.testcases.JuiceBox.pages.BasketPage;
import com.assessment.testcases.JuiceBox.pages.HomePage;
import com.assessment.testcases.JuiceBox.pages.LoginPage;
import com.assessment.testcases.JuiceBox.pages.PaymentPage;
import com.assessment.testcases.JuiceBox.pages.RegistrationPage;

public class BasePageJB {

    private HomePage homePage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private BasketPage basketPage;
    private PaymentPage paymentPage;

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    
    public PaymentPage getPaymentPage() {
        if (paymentPage == null) {
            paymentPage = new PaymentPage();
        }
        return paymentPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    
    public BasketPage getBasketPage() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }

    
    public RegistrationPage getRegistrationPage() {
        if (registrationPage == null) {
            registrationPage = new RegistrationPage();
        }
        return registrationPage;
    }


    private By menuProducts = By.xpath("//span[normalize-space()='Products']");
    private By menuCategory = By.xpath("//span[normalize-space()='Category']");
    private By menuBrand = By.xpath("//span[normalize-space()='Brand']");
    private By menuAddNewProduct = By.xpath("//span[normalize-space()='Add New Product']");
    private By menuAllProducts = By.xpath("//span[normalize-space()='All products']");
    private By messageNotify = By.xpath("//span[@data-notify='message']");
    private By buttonEdit = By.xpath("(//a[@title='Edit'])[1]");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By inputSearch = By.xpath("//input[@id='search']");
    public By avatarProfile = By.xpath("//span[contains(@class,'avatar avatar-sm')]");
    public By buttonCookies = By.xpath("//button[normalize-space()='Ok. I Understood']");


    public void enterDataOnSearchDataTable(String value) {
        setText(inputSearch, value, Keys.ENTER);
    }

    public void clickEditButton() {
        clickElement(buttonEdit);
    }
    public void clickSaveButton() {
        clickElement(buttonSave);
    }

    public String getMessageNotify() {
        return getTextElement(messageNotify);
    }

    public BasePageJB clickMenuProducts() {
        clickElement(menuProducts);
        return this;
    }


}
