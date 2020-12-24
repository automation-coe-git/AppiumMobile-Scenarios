package com.andriod.Examples;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadQRCodeTest {
	private AppiumDriver<MobileElement> driver;
	public static final String URL = "http://127.0.0.1:4723/wd/hub";
	private WebDriverWait wait;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.gamma.scan");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
				"com.gamma.barcodeapp.ui.BarcodeCaptureActivity");
		capabilities.setCapability("unlockType", "pin");
		capabilities.setCapability("unlockKey", "1234");
		URL url = new URL(URL);
		driver = new AndroidDriver<MobileElement>(url, capabilities);

	}

	@Test
	public void readQRCodeDemo() throws IOException, NotFoundException, InterruptedException {
		Thread.sleep(20000);
		driver.findElement(MobileBy
				.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.ImageButton[1]"))
				.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Download')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//android.view.View[@content-desc='Photo taken on Dec 23, 2020 11:28:52 AM']"))
				.click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Open')]")).click();
		Thread.sleep(5000);
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}

	/**
	 * This test capture the screenshot and get the element that contains the
	 * QRCode Based on the element points (width and height) the image os
	 * cropped With cropped image we can decode the QRCode with zxing
	 * 
	 * @throws com.google.zxing.NotFoundException
	 */
	// @Test
	public void readQRCode() throws IOException, NotFoundException, com.google.zxing.NotFoundException {
		MobileElement qrCodeElement = driver.findElement(By.id("com.eliasnogueira.qr_code:id/qrcode"));
		File screenshot = driver.getScreenshotAs(OutputType.FILE);

		String content = decodeQRCode(generateImage(qrCodeElement, screenshot));
		System.out.println("content = " + content);

	}

	/**
	 * Return a cropped image based on an element (in this case the qrcode
	 * image) from the entire device screenshot
	 * 
	 * @param element
	 *            elemement that will show in the screenshot
	 * @param screenshot
	 *            the entire device screenshot
	 * @return a new image in BufferedImage object
	 * @throws IOException
	 *             if any problem in generate image occurs
	 */
	private BufferedImage generateImage(MobileElement element, File screenshot) throws IOException {
		BufferedImage fullImage = ImageIO.read(screenshot);
		Point imageLocation = element.getLocation();

		int qrCodeImageWidth = element.getSize().getWidth();
		int qrCodeImageHeight = element.getSize().getHeight();

		int pointXPosition = imageLocation.getX();
		int pointYPosition = imageLocation.getY();

		BufferedImage qrCodeImage = fullImage.getSubimage(pointXPosition, pointYPosition, qrCodeImageWidth,
				qrCodeImageHeight);
		ImageIO.write(qrCodeImage, "png", screenshot);

		return qrCodeImage;
	}

	/**
	 * Decode a QR Code image using zxing
	 * 
	 * @param qrCodeImage
	 *            the qrcode image cropped from entire device screenshot
	 * @return the content
	 * @throws NotFoundException
	 *             if the image was not found
	 * @throws com.google.zxing.NotFoundException
	 */
	private static String decodeQRCode(BufferedImage qrCodeImage)
			throws NotFoundException, com.google.zxing.NotFoundException {
		LuminanceSource source = new BufferedImageLuminanceSource(qrCodeImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		Result result = new MultiFormatReader().decode(bitmap);
		return result.getText();
	}

	public static void main(String args[])
			throws IOException, com.google.zxing.NotFoundException, ChecksumException, FormatException {
		String imgPath = "D:/rcworksp/Appium-HybridApp/app/QRCode.png";
		qrCodeDecode(imgPath);

		String imgPath2 = "D:/rcworksp/Appium-HybridApp/app/barcode.jpg";
		qrCodeDecode(imgPath2);
	}

	public static void qrCodeDecode(String imgPath)
			throws IOException, com.google.zxing.NotFoundException, ChecksumException, FormatException {
		String code = "";

		// Create instance of Reader
		Reader reader = new MultiFormatReader();

		// Pass the image a parameter, which converts the image into binary
		// bitmap
		InputStream barCodeIS = new FileInputStream(imgPath);
		BufferedImage buffImage = ImageIO.read(barCodeIS);

		LuminanceSource lsrc = new BufferedImageLuminanceSource(buffImage);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(lsrc));

		// Reader decodes the bitmap & extract the data
		Result result = reader.decode(bitmap);

		code = result.getText();
		System.out.println("Image Barcode / QR Code : " + code);
	}

}
