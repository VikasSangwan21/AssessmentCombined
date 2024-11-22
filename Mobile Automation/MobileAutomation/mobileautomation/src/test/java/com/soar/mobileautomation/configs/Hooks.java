package com.soar.mobileautomation.configs;

import java.net.MalformedURLException;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import mobileautomation.utils.AppiumUtils;
import mobileautomation.utils.ExtentReportManager;

public class Hooks {

	private static AppiumDriver driver;
    public static ExtentTest test;
	//private AppiumDriverLocalService appiumService;

	@Before
	public void setup(Scenario scenario) throws MalformedURLException {

        ExtentReportManager.initReports();
        AppiumUtils.startAppiumServer();
        driver = AppiumUtils.setupDriver();
        System.out.println(".........................=============="+driver.toString());
        System.out.println("Appium session started. App is launched.");
        setupTest(scenario); 
        String testName = scenario.getName();
        // Start the Extent Report test
        test = ExtentReportManager.startTest(testName);
        test.info("Starting test: " + testName);
        System.out.println(".........................==============Started Test"+scenario.getName());


    }

    public void setupTest(Scenario scenario) {
        // Dynamically fetch the test name from the Cucumber Scenario
       
    }

    private void endTest(Scenario scenario){
        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
        } else {
            test.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }

        // Add any final info
        test.info("Ending scenario: " + scenario.getName());

        // Flush the report (optional: you may prefer to flush once after all tests)
        ExtentReportManager.flushReports();
    }

    @After
    public void tearDown(Scenario scenario) {
        
		
        if (driver != null) {
            driver.quit();
            System.out.println("Appium session ended. Driver quit.");
        }

        AppiumUtils.stopAppiumServer(); 
        endTest(scenario);
    }

	
    public static AppiumDriver getDriver() {
        return driver;
    }

    public ExtentTest getTest() {
        return test;
    }
}
