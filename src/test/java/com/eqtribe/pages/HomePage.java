package com.eqtribe.pages;

import static com.eqtribe.utilities.PropertyFileReader.ObjRepoProp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.eqtribe.utilities.CustomFunctions;

public class HomePage {

	WebDriver driver;
	CustomFunctions customFunctions;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		customFunctions = new CustomFunctions(driver);

	}

	// Defining Locators(POM)
	By searchField = By.id(ObjRepoProp.getProperty("searchBar_ID"));
	By searchIcon = By.xpath(ObjRepoProp.getProperty("searchIcon_XPATH"));
	By iPhone64GBText = By.xpath(ObjRepoProp.getProperty("iPhone64GBText_XPATH"));
	By iPhone64GBTextFK = By.xpath(ObjRepoProp.getProperty("iPhone64GBTextFK_XPATH"));
	By searchFieldFK = By.xpath(ObjRepoProp.getProperty("searchBarFK_XPATH"));
	By searchIcoFKn = By.xpath(ObjRepoProp.getProperty("searchIconFK_XPATH"));

	public HomePage enterDataInSearchBox(String data) throws Exception {

		customFunctions.typeTxtboxValue(searchField, data);
		customFunctions.doClick(searchIcon);

		return new HomePage(driver);
	}

	public HomePage enterDataInSearchBoxFK(String data1) throws Exception {

		customFunctions.typeTxtboxValue(searchFieldFK, data1);
		customFunctions.doClick(searchIcoFKn);

		return new HomePage(driver);
	}

	public HomePage clickIphoneText() throws Exception {

		customFunctions.doClick(iPhone64GBText);

		return new HomePage(driver);

	}

	public HomePage clickIphoneTextFlipkart() throws Exception {

		customFunctions.doClick(iPhone64GBTextFK);

		return new HomePage(driver);

	}

}
