package com.assessment.testcases.JuiceBox.pages;

import com.assessment.helpers.ExcelHelpers;
import com.assessment.helpers.PropertiesHelpers;
import com.assessment.helpers.SystemHelpers;
import com.assessment.testcases.JuiceBox.BasePageJB;
import com.assessment.utils.DataGenerateUtils;

import static com.assessment.keywords.WebUI.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePageJB {

    
    private By emailTextBox  = By.id("emailControl");
    private By passwordTextBox  = By.id("passwordControl");
    private By repeatPasswordTextBox  = By.id("repeatPasswordControl");
    private By securityQuestionDD  = By.id("mat-select-2");
    private By securityQuestionOptions  = By.cssSelector(".mat-option-text");
    private By securityAnswerTextBox  = By.id("securityAnswerControl");
    private By userRegistrationHeader   = By.xpath("//h1[text()='User Registration']");
    private By passwordAdviceToggle   = By.id("mat-slide-toggle-1-input");
    private By registerButton   = By.id("registerButton");
    private By loginPageHeader   = By.xpath("//h1[text()='Login']");

    // error messages
    private By emailErrorMessage   = By.xpath("//mat-error[text()='Please provide an email address.']");
    private By passwordErrorMessage   = By.xpath("//mat-error[text()='Please provide a password. ']");
    private By repeatPasswordErrorMessage   = By.xpath("//mat-error[text()=' Please repeat your password. ']");
    private By securityQuestionErrorMessage   = By.xpath("//mat-error[text()=' Please select a security question. ']");
    private By securityAnswerrrorMessage   = By.xpath("//mat-error[text()=' Please provide an answer to your security question. ']");

    //passwordadvice
    private By pwdLowerMessage   = By.xpath("//*[text()='contains at least one lower character']");
    private By pwdUpperMessage   = By.xpath("//*[text()='contains at least one upper character']");
    private By pwdDigitMessage   = By.xpath("//*[text()='contains at least one digit']");
    private By pwdSpecialMessage   = By.xpath("//*[text()='contains at least one special character']");
    private By pwdLengthMessage   = By.xpath("//*[text()='contains at least 8 characters']");


    public void inputValidationEmptyFields() {
        clickElement(emailTextBox);
        clickElement(securityQuestionDD);
        sleep(2);  // for test stability
        clickElementWithJs(passwordTextBox);
        clickElement(passwordTextBox);
        sleep(3);  // for test stability
        clickElement(repeatPasswordTextBox);
        clickElement(securityAnswerTextBox);
        clickElement(userRegistrationHeader);

        verifyElementVisible(emailErrorMessage);
        verifyElementVisible(passwordErrorMessage);
        verifyElementVisible(repeatPasswordErrorMessage);
        verifyElementVisible(securityQuestionErrorMessage);
        verifyElementVisible(securityAnswerrrorMessage);
    }

    public void verifyUserRegistrationSuccessful() {
        String[] credentials =  new String[2]; // store username and password in credentials array
        credentials[0] = DataGenerateUtils.randomEmail();
        credentials[1] =   DataGenerateUtils.randomFirstName()+"_12Abc";  
        
        setText(emailTextBox, credentials[0]);
        setText(passwordTextBox, credentials[1]);
        setText(repeatPasswordTextBox, credentials[1]);
        verifyPasswordAdvice();
        //selectOptionByIndex(securityQuestionDD, 1);
        clickElement(securityQuestionDD);
        List<WebElement> options  = getWebElements(securityQuestionOptions);
        clickElement(options.get(1));
        setText(securityAnswerTextBox, DataGenerateUtils.randomFullName());
        clickElement(registerButton);
        verifyElementVisible(loginPageHeader);
        
        // Write Valid credentails to Test Data File
        PropertiesHelpers.loadAllFiles();
        //  Handle Excel file
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(SystemHelpers.getCurrentDir() + PropertiesHelpers.getValue("EXCEL_JS_LOGIN"), "Login");
        excelHelpers.setCellData( credentials[0],1, "email");
        excelHelpers.setCellData(credentials[1], 1, "password");

    }

    public void verifyPasswordAdvice() {
        sleep(2); // for test stability
        clickElementWithJs(passwordAdviceToggle);
        waitForElementVisible(pwdLowerMessage);
        verifyElementVisible(pwdLowerMessage);
        verifyElementVisible(pwdUpperMessage);
        verifyElementVisible(pwdDigitMessage);
        verifyElementVisible(pwdLengthMessage);
        verifyElementVisible(pwdSpecialMessage);

    }


}


