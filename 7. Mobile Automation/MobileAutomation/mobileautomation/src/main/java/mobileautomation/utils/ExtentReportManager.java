package mobileautomation.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    /**
     * Initialize the Extent Report.
     *
     * @return ExtentReports instance.
     */
    public static ExtentReports initReports() {
        PropertiesReader propertiesReader = new PropertiesReader("config/appiumConfig.properties");
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") +propertiesReader.getProperty("reportPath");
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("Soar Mobile Automation Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Platform", "Android");
            extent.setSystemInfo("Tester", "Your Name");
        }
        return extent;
    }

    /**
     * Start a new test.
     *
     * @param testName Name of the test.
     * @return ExtentTest instance.
     */
    public static ExtentTest startTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    /**
     * Flush the Extent Report.
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    /**
     * Get the current test instance.
     *
     * @return ExtentTest instance.
     */
    public static ExtentTest getTest() {
        return test;
    }
}
