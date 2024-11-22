package com.assessment.testcases.JuiceBox.pages;

import com.assessment.helpers.ExcelHelpers;
import com.assessment.helpers.PropertiesHelpers;
import com.assessment.helpers.SystemHelpers;
import com.assessment.keywords.WebUI;
import com.assessment.testcases.JuiceBox.BasePageJB;
import static com.assessment.keywords.WebUI.*;

import org.openqa.selenium.By;

public class LoginPage extends BasePageJB {

    
    private By notYetACustomerLink  = By.linkText("Not yet a customer?");
    private By userRegistrationHeader   = By.xpath("//h1[text()='User Registration']");
    
    private By emailTextBox  = By.id("email");
    private By passwordTextBox  = By.id("password");
    private By loginButton  = By.id("loginButton");
    private By cart  = By.xpath("//*[text()=' shopping_cart ']");


    public void navigateToRegistrationPage() {
        clickElement(notYetACustomerLink);
        verifyElementVisible(userRegistrationHeader);
    }

    
    public void verifySuccessfulLogin() {
        // Get credentials from test data file
         WebUI.logConsole(SystemHelpers.getCurrentDir() + PropertiesHelpers.getValue("EXCEL_JS_LOGIN"));
        //  Handle Excel file
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(SystemHelpers.getCurrentDir() + PropertiesHelpers.getValue("EXCEL_JS_LOGIN"), "Login");

        setText(emailTextBox, excelHelpers.getCellData(1, "email"));
        setText(passwordTextBox, excelHelpers.getCellData(1, "password"));
        clickElement(loginButton);
        waitForElementPresent(cart);
        verifyElementVisible(cart);
    }


}


