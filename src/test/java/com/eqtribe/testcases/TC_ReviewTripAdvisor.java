package com.eqtribe.testcases;

import static com.eqtribe.utilities.PropertyFileReader.ObjRepoProp;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eqtribe.base.BaseClass;
import com.eqtribe.pages.HomePage;
import com.eqtribe.pages.HomePageTripAdvisor;
import com.eqtribe.utilities.JsonReader;

public class TC_ReviewTripAdvisor extends BaseClass {

	Properties prop;
	HomePageTripAdvisor homepage1;

	/**
	 * Access TripAdivisor URL
	 * 
	 * @throws Exception
	 */

	@Test(priority = 1)
	public void AccessTripAdvisorURL() throws Exception {
		test = extent.createTest("AccessTripAdvisorURL");
		driver.get(JsonReader.getEnvironmentInfo("stage").getString("url3"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		logger.info("URL is Opened");

		System.out.println(driver.getTitle());

		homepage1 = new HomePageTripAdvisor(driver);

		homepage1.clickSearchIcon();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(ObjRepoProp.getProperty("mainSearch_ID"))));

		homepage1.enterDataInSearchBox(JsonReader.getEnvironmentInfo("stage").getString("searchText"));

		List<WebElement> title = driver.findElements(By.xpath("//div[@class='result-title']"));

		for (int i = 0; i < title.size(); i++) {

			title.get(i).click();
			if (i == 1) {
				// breaking the loop
				break;
			}

		}
		String parentWinHandle = driver.getWindowHandle();
		System.out.println("Parent window handle: " + parentWinHandle);

		Set<String> winHandles = driver.getWindowHandles();
		// Loop through all window handles
		for (String handle : winHandles) {
			if (!handle.equals(parentWinHandle)) {
				driver.switchTo().window(handle);

			}

		}

		driver.findElement(By.xpath(ObjRepoProp.getProperty("reviewBtn_XPATH"))).click();

		Thread.sleep(2000);

	}

}
