package com.soar.mobileautomation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.soar.mobileautomation.configs.Hooks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BasePage {
	
    /**
     * Method used to input text in to text fields
     * @param inputText
     * @param WebElement
     * @param driver
     */
	public void inputText(String inputText, By by,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(inputText);
		// if (driver.getKeyboard() != null) {
		// 	closeKeyboard(driver);
		// }
	}
	/**
	 * Method used to click button elements
	 * @param WebElement
	 * @param driver
	 */
	public void clickButton(WebElement WebElement, @SuppressWarnings("rawtypes") AppiumDriver driver) {
		waitForVisibilityOf(WebElement, driver);
		WebElement.click();
	}

		/**
	 * Method used to click button elements
	 * @param WebElement
	 * @param driver
	 */
	public void clickButton(By by, @SuppressWarnings("rawtypes") AppiumDriver driver) {
		WebElement element = driver.findElement(by);
		waitForVisibilityOf(element, driver);
		element.click();
		Hooks.test.log(Status.PASS, "Clicked Element Passed");
	}


	/**
     * Verifies if more than one search result is displayed.
     *
     * @param locator The By locator to find the list of elements (search results).
     * @return True if more than one search result is displayed, false otherwise.
     */
    public boolean areMultipleSearchResultsDisplayed(By locator, AppiumDriver driver) {
        try {
            List<WebElement> searchResults = driver.findElements(locator);

            // Check if there are more than one search result and they are displayed
            if (searchResults.size() > 1) {
                for (WebElement result : searchResults) {
                    if (!result.isDisplayed()) {
                        System.out.println("Search result not displayed: " + result.toString());
                        return false;
                    }
                }
                return true; // More than one result, and all are displayed
            } else {
                System.out.println("Less than two search results found.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error verifying search results: " + e.getMessage());
            return false;
        }
    }

	/**
	 * Method used to get text
	 * @param WebElement
	 * @return
	 */
	public String getText(By by,  AppiumDriver driver) {
		WebElement element = driver.findElement(by);
		waitForVisibilityOf(element, driver);
		return element.getText();
	}
	/**
	 * Method used to wait for visibility of element
	 * @param WebElement
	 * @param driver
	 */
	protected void waitForVisibilityOf(WebElement webElement, AppiumDriver driver) {
		// Use Duration.ofSeconds() to specify the timeout
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(90));
		webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
	}
	/**
	 * Method used to wait for availability of element
	 * @param WebElement
	 * @param driver
	 */
	protected void waitForAvailabilityOf(WebElement WebElement,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(90));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(WebElement));
	}
	protected void waitForVisibilityOfShort(WebElement WebElement,
			@SuppressWarnings("rawtypes") AppiumDriver driver) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
		webDriverWait.until(ExpectedConditions.visibilityOf(WebElement));
	}
	

	/**
	 * Method used to close keyboard or hide keyboard
	 * @param driver
	 */
	public static void closeKeyboard(AppiumDriver driver, String platform) {
			platform = platform.toLowerCase(); // Ensure platform name is case-insensitive

			switch (platform) {
				case "ios":
					if (driver instanceof IOSDriver) {
						try {
							// Attempt to click the "Done" button on the iOS keyboard toolbar
							WebElement doneToolBarButton = driver.findElement(By.xpath("//*[@label='Done']"));
							if (doneToolBarButton.isDisplayed()) {
								doneToolBarButton.click();
								System.out.println("Clicked the Done button to close the keyboard on iOS.");
							}
						} catch (NoSuchElementException e) {
							System.err.println("Done button not found. Skipping keyboard close action for iOS.");
						}
					} else {
						System.err.println("Driver is not an instance of IOSDriver. Cannot perform iOS-specific actions.");
					}
					break;

				case "android":
					if (driver instanceof AndroidDriver) {
						try {
							// Hide keyboard for Android
							((AndroidDriver) driver).hideKeyboard();
							System.out.println("Keyboard hidden on Android.");
						} catch (Exception e) {
							System.err.println("Error hiding the keyboard on Android: " + e.getMessage());
						}
					} else {
						System.err.println("Driver is not an instance of AndroidDriver. Cannot perform Android-specific actions.");
					}
					break;

				default:
					System.err.println("Unsupported platform: " + platform);
					break;
			}
		}


    /**
     * Verifies if an element is displayed on the screen.
     *
     * @param locator The By locator to identify the element.
     * @return True if the element is displayed, false otherwise.
     */
    public boolean isElementDisplayed(By locator, AppiumDriver driver) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            System.err.println("Element not found with locator: " + locator.toString());
            return false;
        }
    }
	

	 
}
