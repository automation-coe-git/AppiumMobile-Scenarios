package com.andriod.Examples;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class webViewNativeApp {
	AndroidDriver<AndroidElement> driver;
	public static final String URL = "http://127.0.0.1:4723/wd/hub";
	@BeforeSuite
	public void setup() throws Exception {

		DesiredCapabilities caps = new DesiredCapabilities();

		// Specify device and os_version for testing
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.0");

		// Set other BrowserStack capabilities
		caps.setCapability("build", "Java Android");
		caps.setCapability("deviceName", "emulator-5554");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("noSign", "true");


		URL url = new URL(URL);

		driver = new AndroidDriver<AndroidElement>(url, caps);
	}
	@Test
	public void webViewNativeAppTest() throws InterruptedException {
		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Apps");
		el1.click();
		MobileElement el2 = (MobileElement) driver.findElementById("com.android.launcher3:id/search_box_input");
		el2.click();
		el2.sendKeys("quora");
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("Quora");
		el3.click();
		MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("Search");
		el4.click();
		MobileElement el5 = (MobileElement) driver.findElementById("com.quora.android:id/lookup_edittext");
		el5.click();
		MobileElement el6 = (MobileElement) driver.findElementById("com.quora.android:id/lookup_edittext");
		el6.click();
		el6.sendKeys("Appium");
		MobileElement el7 = (MobileElement) driver
				.findElementByAccessibilityId("Appium (test automation) Topic: Appium (test automation)");
		el7.click();
		MobileElement el9 = (MobileElement) driver.findElementByAccessibilityId("Back");
		el9.click();
		MobileElement el10 = (MobileElement) driver
				.findElementByAccessibilityId("Appium (test automation) Topic: Appium (test automation)");
		el10.click();
		MobileElement el11 = (MobileElement) driver.findElementByAccessibilityId("Answer ");
		el11.click();
		MobileElement el12 = (MobileElement) driver.findElementByAccessibilityId("Answer ");
		el12.click();
		MobileElement el13 = (MobileElement) driver.findElementByAccessibilityId("Search");
		el13.click();
		MobileElement el14 = (MobileElement) driver.findElementById("com.quora.android:id/lookup_edittext");
		el14.click();
		MobileElement el15 = (MobileElement) driver.findElementById("com.quora.android:id/lookup_edittext");
		el15.click();
		el15.sendKeys("webview");
		MobileElement el16 = (MobileElement) driver
				.findElementByAccessibilityId("Android System WebView Topic: Android System WebView");
		el16.click();
		MobileElement el17 = (MobileElement) driver.findElementByAccessibilityId("Answer ");
		el17.click();	
	}
}