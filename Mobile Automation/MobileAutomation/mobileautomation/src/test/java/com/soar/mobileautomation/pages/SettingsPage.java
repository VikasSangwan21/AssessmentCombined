package com.soar.mobileautomation.pages;

import org.openqa.selenium.By;
import com.soar.mobileautomation.configs.Hooks;
import io.appium.java_client.AppiumDriver;



public class SettingsPage extends BasePage {
	@SuppressWarnings("rawtypes")
	public AppiumDriver driver;
	/*
	 *    Constructor initializes screen elements
	 */
	@SuppressWarnings("static-access")
	public SettingsPage(){
		Hooks hooks = new Hooks();
		this.driver = hooks.getDriver();
	}

    By showImages = By.xpath("(//android.widget.Switch[@resource-id=\"org.wikipedia.alpha:id/switchWidget\"])[1]");
	By showLinkPreviews = By.xpath("(//android.widget.Switch[@resource-id=\"org.wikipedia.alpha:id/switchWidget\"])[2]");
	By sendUsageReports = By.xpath("(//android.widget.Switch[@resource-id=\"org.wikipedia.alpha:id/switchWidget\"])[3]");
	By sendCrashReports = By.xpath("(//android.widget.Switch[@resource-id=\"org.wikipedia.alpha:id/switchWidget\"])[4]");
	By backToHomeButton = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");

	

	
	public void disableAllSettings() {
		if(getText(showImages, driver).equals("ON")){
			clickButton(showImages, driver);
		}

		if(getText(showLinkPreviews, driver).equals("ON")){
			clickButton(showLinkPreviews, driver);
		}

		if(getText(sendUsageReports, driver).equals("ON")){
			clickButton(sendUsageReports, driver);
		}

		if(getText(sendCrashReports, driver).equals("ON")){
			clickButton(sendCrashReports, driver);
		}
	}

	public void navigateBackToHome() {
		clickButton(backToHomeButton, driver);
	}

	
}
