package com.andriod.Examples;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FingerprintTest {
	WebDriver driver;
	private String securityText = "//android.widget.TextView[@text='Security']";
	private String touchSensorText = "//android.widget.TextView[@text='Touch the sensor']";
	private String doneText = "//android.widget.Button[@text='DONE']";

	//@Test
	public void fingerPrintSetup() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");

		caps.setCapability("deviceName", "emulator-5554");
		caps.setCapability("appPackage", "com.android.settings");
		caps.setCapability("appActivity", "com.android.settings.Settings");
		caps.setCapability("automationName", "uiautomator2");
		//caps.setCapability("unlockType", "pin"); 
		//caps.setCapability("unlockKey", "1234");

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			Thread.sleep(5000);
			TouchAction action = new TouchAction(((AndroidDriver) driver));
			int height = driver.manage().window().getSize().height;
			int width = driver.manage().window().getSize().width;
			System.out.println("Dimensions of screen ->  " + width + " x " + height);
			driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Search settings']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.EditText[contains(@text,'Search…')]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.EditText[contains(@text,'Search…')]")).sendKeys("Security");
			/*action.press(new PointOption().point(70, height - 1000))
					.waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
					.moveTo(new PointOption().point(70, 200)).release();
			MultiTouchAction multiAction = new MultiTouchAction(((AndroidDriver) driver));
			multiAction.add(action).perform();
			Thread.sleep(5000);*/

			WaitUntilElementIsDisplayed(By.xpath("//android.widget.TextView[contains(@text,'Security')]"), 10);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Security')]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[@text='Fingerprint']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[@text='Continue']")).click();
			Thread.sleep(5000);
			//driver.findElement(By.xpath("//android.widget.Button[@text='NEXT']")).click();
			//Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[@text='Fingerprint + PIN']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[@text='No thanks']")).click();
			
			/*driver.findElement(By.xpath("//android.widget.Button[@text='YES']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();*/
			WebElement pinclick = driver.findElement(
					By.xpath("//android.widget.EditText[@resource-id='com.android.settings:id/password_entry']"));
			pinclick.click();
			WebElement pin = driver.findElement(
					By.xpath("//android.widget.EditText[@resource-id='com.android.settings:id/password_entry']"));
			
			pin.sendKeys("1234");
			driver.findElement(By.xpath("//android.widget.Button[@text='CONTINUE']")).click();
			Thread.sleep(2000);
			pin.sendKeys("1234");
			driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//android.widget.Button[@text='DONE']")).click();

			// Let's assume you are on fingerprint page
			// send the fingerprint
			((AndroidDriver) driver).fingerPrint(1);

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(driver.getPageSource());

		} finally {

			driver.quit();
		}
	}
	
	@Test
	public void fingerPrintAction() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");

		caps.setCapability("deviceName", "emulator-5554");
		caps.setCapability("appPackage", "com.android.settings");
	caps.setCapability("appActivity", "com.android.settings.Settings");
		caps.setCapability("automationName", "uiautomator2");
		caps.setCapability("unlockType", "pin"); 
		caps.setCapability("unlockKey", "1234");

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			Thread.sleep(5000);
			TouchAction action = new TouchAction(((AndroidDriver) driver));
			int height = driver.manage().window().getSize().height;
			int width = driver.manage().window().getSize().width;
			System.out.println("Dimensions of screen ->  " + width + " x " + height);
			driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Search settings']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.EditText[contains(@text,'Search…')]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.EditText[contains(@text,'Search…')]")).sendKeys("Security");
			/*action.press(new PointOption().point(70, height - 1000))
					.waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
					.moveTo(new PointOption().point(70, 200)).release();
			MultiTouchAction multiAction = new MultiTouchAction(((AndroidDriver) driver));
			multiAction.add(action).perform();
			Thread.sleep(5000);*/

			WaitUntilElementIsDisplayed(By.xpath("//android.widget.TextView[contains(@text,'Security')]"), 10);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Security')]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[@text='Fingerprint']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//android.widget.TextView[@text='Fingerprint']")).click();
			WebElement pinclick = driver.findElement(
					By.xpath("//android.widget.EditText[@resource-id='com.android.settings:id/password_entry']"));
			pinclick.click();
			WebElement pin = driver.findElement(
					By.xpath("//android.widget.EditText[@resource-id='com.android.settings:id/password_entry']"));
			
			pin.sendKeys("1234");
			 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			 Thread.sleep(10000);
				driver.findElement(By.xpath("//android.widget.TextView[@text='Add fingerprint']")).click();
				
				WebElement fingerPress = driver.findElement(
						By.id("com.android.settings:id/fingerprint_animator"));
				
				fingerPress.click();
				fingerPress.click();
				fingerPress.click();
				 Thread.sleep(5000);
				 driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
				 System.out.println("Please touch finger to validate");
	} catch (Exception ex) {
		ex.printStackTrace();
		System.out.println(driver.getPageSource());

	} finally {

		//driver.quit();
	
	}
	}

	protected boolean WaitUntilElementIsDisplayed(By by, int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception ex) {
			System.out.println("Exception while waiting {ex.Message}" + ex.getMessage());
			return false;
		}
	}

}
