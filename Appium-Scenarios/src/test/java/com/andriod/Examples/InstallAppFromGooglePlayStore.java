package com.andriod.Examples;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class InstallAppFromGooglePlayStore {

	private AppiumDriver driver;
	private WebDriverWait wait;
	private long explicitWaitTimeoutInSeconds = 10L;
	private static long INSTALL_DURATION_IN_SECONDS = 60L;

	
	final String testAppName = "Simple Notepad";
	final String testAppPackage = "org.mightyfrog.android.simplenotepad";
	final String testAppActivity = "org.mightyfrog.android.simplenotepad.SimpleNotepad";

	@Before
	public void setUp() throws Exception {
		
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".AssetBrowserActivity");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, ".AssetBrowserActivity");
		capabilities.setCapability(AndroidMobileCapabilityType.DEVICE_READY_TIMEOUT, 40);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
		capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.18.3");
		capabilities.setCapability("deviceOrientation", "portrait");
		capabilities.setCapability("unicodeKeyboard", "true");
		capabilities.setCapability("unlockType", "pin"); 
		capabilities.setCapability("unlockKey", "1234");
		this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		//swipeToUnlock();
		this.wait = new WebDriverWait(driver, explicitWaitTimeoutInSeconds);
	

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	

	 @Test
	    public void installAppFromGooglePlayStore() throws Exception {
		 
		 Thread.sleep(20000);
		 // wait until search bar is visible, and then tap on it
	        wait.until(ExpectedConditions.visibilityOf(
	                driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.android.vending:id/mini_blurb\")"))))
	                .click();
		 Thread.sleep(3000);
		 wait.until(ExpectedConditions.visibilityOf(
	                driver.findElement(MobileBy.AccessibilityId("Search Google Play"))))
	                .click();
		 Thread.sleep(3000);
		 wait.until(ExpectedConditions.visibilityOf(
	                driver.findElement(MobileBy.className("android.widget.EditText"))))
	                .click();
		 wait.until(ExpectedConditions.visibilityOf(
	                driver.findElement(MobileBy.className("android.widget.EditText"))))
	                .sendKeys(testAppName.toLowerCase());
		 Thread.sleep(10000);
		 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		 Thread.sleep(10000);
		 MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("App: Simple Notepad\nmightyfrog.org\nProductivity\nStar rating: 4.3\n427 KB\n1,000,000+ downloads\n");
		 el1.click();
		 Thread.sleep(5000);
		// tap on the Install button
		
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Install\")")))).click();
		 Thread.sleep(30000);  
		 System.out.println("Installing Notepad app has been completed sucessfully ");
		 launchApp();
		 Thread.sleep(8000);
		 driver.removeApp(testAppPackage);
		 System.out.println("Uninstalling Notepad app has been completed sucessfully ");
	    }

	 
	public void launchApp() throws Exception {

		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, testAppPackage);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, testAppActivity);
		this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("Launching Notepad app... ");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"ALLOW\")")))).click();
	}


}
