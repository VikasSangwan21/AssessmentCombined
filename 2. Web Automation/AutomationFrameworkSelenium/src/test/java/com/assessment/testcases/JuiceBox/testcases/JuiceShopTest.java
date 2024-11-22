package com.assessment.testcases.JuiceBox.testcases;

import com.assessment.common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Epic("Regression Test CMS")
@Feature("Login Test")
public class JuiceShopTest extends BaseTest {
    
    @Test(priority = 1)
    public void Task1_Verify_Maximum_Items_Per_Page() {
        getHomePage().openJBHomePageScrollToBottom();
        getHomePage().verifyMaximumItemsPerPage();
    }

    @Test(priority = 1)
    public void Task2_Review_First_Product() {
        getHomePage().clickProductAndVerifyPopup(1);
    }

    
    @Test(priority = 1)
    public void Task3_Verify_User_Registration() {
        getHomePage().navigateToLoginPage();
        getLoginPage().navigateToRegistrationPage();
        getRegistrationPage().inputValidationEmptyFields();
        getRegistrationPage().verifyUserRegistrationSuccessful();
        getLoginPage().verifySuccessfulLogin();
    }
   
    
    @Test(priority = 1)
    public void Task4_Verify_Checkout_Flow() {
        getHomePage().navigateToLoginPage();
        getLoginPage().verifySuccessfulLogin();
        getHomePage().addProductsToBasket(5);
        getHomePage().navigateToBasket();
        getBasketPage().increaseItemQtyAndDeleteItem();
        getBasketPage().checkoutToPaymentPage();
        getPaymentPage().addNewCard();
        getPaymentPage().selectCardAndCompletePayment();

    }

  

    

}
