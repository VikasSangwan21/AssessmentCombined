package mobileautomation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumUtils {

	
	private static AppiumDriverLocalService appiumService;
	public static void startAppiumServer(){
		
		appiumService = new AppiumServiceBuilder().build();
		appiumService.start();
		System.out.println("Appium Server Started: " + appiumService.getUrl());
	}

	public static void stopAppiumServer(){
		if (appiumService != null && appiumService.isRunning()) {
			appiumService.stop();
			System.out.println("Appium Server Stopped.");
		}
	}

	public static AppiumDriver setupDriver() throws MalformedURLException{
		PropertiesReader propertiesReader = new PropertiesReader("config/appiumConfig.properties");
          

		// Set the desired capabilities using UiAutomator2Options
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(propertiesReader.getProperty("platform"))
				.setPlatformVersion(propertiesReader.getProperty("version"))
                .setDeviceName(propertiesReader.getProperty("deviceName"))
				.setAutomationName(propertiesReader.getProperty("automationName"))
				.setApp("C:/Users/user/Desktop/SOAR/Mobile Automation/MobileAutomation/mobileautomation/src/test/resources/application/WikipediaSample.apk")
				//.setApp(System.getProperty("user.dir") +propertiesReader.getProperty("applicationApk"))
                .setNoReset(true) // Keeps the app data between sessions
                .setFullReset(false); // Does not uninstall the app between sessions

        // Initialize the Appium driver
        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
	}

	public static String getProperty(String key)
	{
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream("./config/appiumConfig.properties");
			prop.load(input);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return prop.getProperty(key);
	}
	
}
