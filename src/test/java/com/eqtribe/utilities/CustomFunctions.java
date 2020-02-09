package com.eqtribe.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomFunctions {

	WebDriver driver;

	public CustomFunctions(WebDriver driver) {

		this.driver = driver;
	}

	public WebElement getElement(By locator) {

		waitForElementPresent(locator);

		WebElement element = null;
		try {
			element = driver.findElement(locator);
			// JavaScriptUtil.flash(element, driver);
		} catch (Exception e) {

			System.out.println("Exception occured while creating webelemnt " + locator);
		}
		return element;
	}

	public void waitForElementPresent(By by) {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));

	}

	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("Exception occured while clicking on webelemnt " + locator);

		}

	}

	public void typeTxtboxValue(By locator, String value) {

		try {
			getElement(locator).clear();
			getElement(locator).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Exception occured while entering text on webelement " + locator);

		}

	}

	public boolean isElementDisplayed(By locator) {

		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("Exception occured while verifying the WebElement " + locator);
			return false;
		}

	}

	public static String getCurrentDateTime() {

		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");

		Date currentDate = new Date();

		return customFormat.format(currentDate);
	}

}
