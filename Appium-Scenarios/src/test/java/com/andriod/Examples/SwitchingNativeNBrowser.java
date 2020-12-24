package com.andriod.Examples;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SwitchingNativeNBrowser  {
	AndroidDriver<AndroidElement> driver;
	public static final String URL = "http://127.0.0.1:4723/wd/hub";
	private WebDriverWait wait;
	private long explicitWaitTimeoutInSeconds = 10L;
	
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
		caps.setCapability("unlockType", "pin"); 
		caps.setCapability("unlockKey", "1234");


		URL url = new URL(URL);

		this.driver  = new AndroidDriver<AndroidElement>(url, caps);
		this.wait = new WebDriverWait(driver, explicitWaitTimeoutInSeconds);
	}
	@SuppressWarnings("rawtypes")
	@Test
	public void switchingNativeAppNBrowser() throws InterruptedException {
		Thread.sleep(5000);
		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Gmail");
		el1.click();
		Thread.sleep(10000);
		MobileElement ele = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@content-desc='Search']");
		ele.click();
		Thread.sleep(5000);
		MobileElement ele1search = (MobileElement) driver.findElementById("com.google.android.gm:id/search_actionbar_query_text");
		ele1search.click();
		MobileElement ele1searchmail = (MobileElement) driver.findElementById("com.google.android.gm:id/search_actionbar_query_text");
		ele1searchmail.sendKeys("raghu rc");
		
		 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		 Thread.sleep(10000);
		 
		 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
		 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
		 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
		 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
		 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.DPAD_UP));
		 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		 Thread.sleep(15000);
		 wait.until(ExpectedConditions.visibilityOf(
	                driver.findElement(MobileBy.AccessibilityId("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx"))))
	                .click();
		Thread.sleep(10000);
		MobileElement el4 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Username:\"]");
		el4.click();
		el4.sendKeys("Tester");
		Thread.sleep(5000);
		 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.TAB));
		 MobileElement el7 = (MobileElement) driver.findElementByXPath("//android.webkit.WebView[@content-desc=\"Web Orders Login\"]/android.view.View/android.view.View[1]/android.widget.EditText[2]");
		 el7.sendKeys("test");
		Thread.sleep(5000);
		MobileElement el8 = (MobileElement) driver.findElementByAccessibilityId("Login");
		el8.click();
		Thread.sleep(5000);
		
		activateApp("com.google.android.gm");
		Thread.sleep(3000);
		activateApp("com.android.chrome");
	}
	public void activateApp(String appPackageName){
		driver.activateApp(appPackageName);

	}

}