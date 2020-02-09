package com.eqtribe.pages;

import static com.eqtribe.utilities.PropertyFileReader.ObjRepoProp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.eqtribe.utilities.CustomFunctions;

public class HomePageTripAdvisor {

	WebDriver driver;
	CustomFunctions customFunctions;

	public HomePageTripAdvisor(WebDriver driver) {

		this.driver = driver;
		customFunctions = new CustomFunctions(driver);

	}

	// Defining Locators(POM)
	By searchField = By.xpath(ObjRepoProp.getProperty("searchBar_XPATH"));
	By mainSearchField = By.id(ObjRepoProp.getProperty("mainSearch_ID"));
	By searchBtn = By.id(ObjRepoProp.getProperty("searchBtn_ID"));

	public HomePage enterDataInSearchBox(String data) throws Exception {

		customFunctions.typeTxtboxValue(mainSearchField, data);
		customFunctions.doClick(searchBtn);

		return new HomePage(driver);
	}

	public HomePage clickSearchIcon() throws Exception {

		WebElement element = driver.findElement(searchField);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		return new HomePage(driver);

	}

}
