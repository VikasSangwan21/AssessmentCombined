package com.soar.mobileautomation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.soar.mobileautomation.stepdefinitions", "com.soar.mobileautomation"}, 
        dryRun = false,
        plugin = {
                "html:target/cucumber-reports/cucumber-pretty", 
                "json:target/cucumber-reports/CucumberTestReport.json",
                "pretty",
                "rerun:target/cucumber-reports/rerun.txt"
        })

public class TestRunner extends AbstractTestNGCucumberTests {
}
