
## Test Automation Framework Selenium Java with TestNG by Vikas Sangwan

🔆 **SOME FEATURES IN FRAMEWORK**

1. Run the parallel test case
2. Read Config from Properties file
3. Extent Report
4. Allure Report
5. Write Log to file
6. Record Screenshot  for failed and passed  test case
7. Read data test from Excel file (xlsx, csv, json,...)
8. Base function in the package: utils, helpers
9. Main Keyword: WebUI (call common function)
10. Sample test all function in WebUI keyword
11. Run Selenium Grid (remote)
12. Use DataFaker and JavaFaker to generate data
13. Retry Failed Test in TestNG with IRetryAnalyzer and IAnnotationTransformer
14. Javadoc for this source

### ✳️ **SYSTEM REQUIREMENTS**

- Install JDK (recommend JDK >= 11)
- Install Chrome Browser, Edge Browser, Firefox Browser
- Run well on the **Windows** platform
- Setup **Allure ENV**:


**1. Run the test case**

- Run test case in suite XML (**src/test/resources/suites/**)
- Run test case from Maven pom.xml file
  (**mvn clean test**)


**Test Configuration**

- The test execution can be modified by updating the variables in config.properties file present in path below:
- src\test\resources\config\config.properties



**Test Results**

- The test results can be found unde report folder
- depending on the configuration the results will be overwritten or saved separtely
- Screenshots are stored in ExportData folderS
