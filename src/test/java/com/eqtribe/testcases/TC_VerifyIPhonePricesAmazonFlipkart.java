package com.eqtribe.testcases;

import static com.eqtribe.utilities.PropertyFileReader.ObjRepoProp;

import java.util.ArrayList;

import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eqtribe.base.BaseClass;
import com.eqtribe.pages.HomePage;
import com.eqtribe.utilities.JsonReader;

/**
 * @author Rajashekar Sadhula
 */
public class TC_VerifyIPhonePricesAmazonFlipkart extends BaseClass {

	Properties prop;
	HomePage homepage;

	int X; // Amazon
	int Y; // FlipKart

	/**
	 * Access AMAZON URL
	 * 
	 * @throws Exception
	 */

	@Test(priority = 1)
	public void AccessAmazonURL() throws Exception {
		test = extent.createTest("AccessAmazonURL");
		driver.get(baseURL);

		logger.info("URL is Opened");

	}

	/**
	 * Search Iphone Product & get the price
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void searchIphoneGetPrice() throws Exception {

		test = extent.createTest("searchIphoneGetPrice");

		homepage = new HomePage(driver);

		homepage.enterDataInSearchBox(JsonReader.getEnvironmentInfo("stage").getString("iPhoneText"));
		logger.info("Successfully entered iphone text");
		Assert.assertEquals(driver.getTitle(), JsonReader.getEnvironmentInfo("stage").getString("title1"));
		homepage.clickIphoneText();

		String parentWinHandle = driver.getWindowHandle();
		System.out.println("Parent window handle: " + parentWinHandle);

		Set<String> winHandles = driver.getWindowHandles();
		// Loop through all window handles
		for (String handle : winHandles) {
			if (!handle.equals(parentWinHandle)) {
				driver.switchTo().window(handle);
				Assert.assertEquals(driver.getTitle(), JsonReader.getEnvironmentInfo("stage").getString("title2"));
			}

		}

		// analyzing the string

		String IphonePriceAmazon = driver.findElement(By.xpath(ObjRepoProp.getProperty("amazonIphonePrice_XPATH")))
				.getText();
		// Removing junk values
		String price1 = IphonePriceAmazon.replaceAll("[^0-9]", "");
		// Removing last two 0's from price
		String finalprice1 = price1.substring(0, price1.length() - 2);
		// Converting string to integer
		X = Integer.parseInt(finalprice1);

		System.out.println(X);

		logger.info("Iphone price in amazon got fetched & stored in variable");

	}

	/**
	 * Access Flipkart & Get the price of Iphone
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void goToFlipKartFetchIphonePrice() throws Exception {

		test = extent.createTest("goToFlipKartFetchIphonePrice");
		// Open new tab & Access Flipkart site
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); // switches to new tab

		driver.get(JsonReader.getEnvironmentInfo("stage").getString("url2"));
		// Click on close icon
		driver.findElement(By.xpath(ObjRepoProp.getProperty("closeIconFK_XPATH"))).click();

		homepage = new HomePage(driver);

		homepage.enterDataInSearchBoxFK(JsonReader.getEnvironmentInfo("stage").getString("iPhoneText"));

		homepage.clickIphoneTextFlipkart();

		String parentWinHandle = driver.getWindowHandle();
		System.out.println("Parent window handle: " + parentWinHandle);

		Set<String> winHandles = driver.getWindowHandles();
		// Loop through all handles
		for (String handle : winHandles) {
			if (!handle.equals(parentWinHandle)) {
				driver.switchTo().window(handle);
			}
			Thread.sleep(1000);
			System.out.println("Title of the new window: " + driver.getTitle());

		}
		// analyzing the string

		String IphonePriceFlipKart = driver.findElement(By.xpath(ObjRepoProp.getProperty("flipKartIphonePrice_XPATH")))
				.getText();
		// Remove junk values

		String price2 = IphonePriceFlipKart.replaceAll("[^0-9]", "");
		// Converting string to integer
		Y = Integer.parseInt(price2);

		System.out.println(Y);
		logger.info("Iphone price in flipkart got fetched & stored in variable");

	}

	/**
	 * Compare Amazon & Flipkart Prices for Iphone
	 * 
	 * @throws Exception
	 */
	@Test(priority = 4)
	public void compareValues() throws Exception {
		test = extent.createTest("compareValues");

		// X - AMAZON
		// Y - FLIPKART

		if (X > Y) {

			System.out.println("iPhone XR (64GB) price is more in Amazon than Flipkart");
			System.out.println("iPhone XR (64GB) price in AMAZON " + X);
			System.out.println("iPhone XR (64GB) price in FLIPKART " + Y);
		} else {

			System.out.println("iPhone XR (64GB) price is more in FlipKart than Amazon");
			System.out.println("iPhone XR (64GB) price in AMAZON " + X);
			System.out.println("iPhone XR (64GB) price in FLIPKART " + Y);
		}
		logger.info("Compared Prices in Amazon & Flipakart & displays which is high/less for iphone6");
	}
}
