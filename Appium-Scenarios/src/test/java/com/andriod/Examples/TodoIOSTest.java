package com.andriod.Examples;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class TodoIOSTest {

	@Test
	public void testeToDo() throws MalformedURLException {
		// capacidades
		File app = new File("src/test/resources/todo/ios/ToDo.app");
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
		dc.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
		dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		// sessao
		//IOSDriver<WebElement> driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		 IOSDriver<WebElement> driver = new IOSDriver<WebElement>(
		            new URL("http://127.0.0.1:4723/wd/hub"), dc);
		
		driver.findElement(By.id("titulo")).sendKeys("Lavar o carro");
		driver.findElement(By.cssSelector(".button.button-positive.button-small")).click();
		
		// validação
		assertTrue(driver.findElement(By.xpath("//span[text()='Lavar o carro']")).isDisplayed());
		
		driver.quit();
	}

}
