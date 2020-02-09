package com.eqtribe.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.eqtribe.utilities.ConfigDataProvider;
import com.eqtribe.utilities.DataStore;
import com.eqtribe.utilities.JsonReader;
import com.eqtribe.utilities.PropertyFileReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;


public class BaseClass {

	JsonReader read = new JsonReader();

	public String baseURL = JsonReader.getEnvironmentInfo("stage").getString("url");
	public static WebDriver driver;
	public static Logger logger;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void suiteSetUp() {
		// time stamp
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";
		// specify location of the report
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "clubkitchen");
		extent.setSystemInfo("user", "Rajashekar");
		// Tile of report
		htmlReporter.config().setDocumentTitle("clubkitchen Test Project");
		// name of the report
		htmlReporter.config().setReportName("Functional Test Automation Report");
		// location of the chart
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@AfterMethod
	public void getResult(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			test.fail(MarkupHelper.createLabel(result.getName() + "Test Case Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {

			test.pass(MarkupHelper.createLabel(result.getName() + "Test Case Passed", ExtentColor.GREEN));
		} else {

			test.skip(MarkupHelper.createLabel(result.getName() + "Test Case Skipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}

	}

	@Parameters("browser")
	@BeforeClass
	public void setUp(String browserName) {

		ConfigDataProvider config = new ConfigDataProvider();

		logger = Logger.getLogger("appbuilder");
		PropertyConfigurator.configure("Log4j.properties");
		JsonReader.getJSONConfigData();
		PropertyFileReader.loadProprtyFile();
		
		 
		if (browserName.equals(config.getBrowser())) {
			
		        WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
		        driver = new ChromeDriver(options);
		    

		} else if (browserName.equals(config.getBrowser())) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else {

			System.out.println("We do not support this browser/Invalid browser");
		}
		// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		//driver.get(DataStore.getEnvironmentInfo("stage").getString("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

	@AfterSuite
	public void suitetearDown() {

		extent.flush();
	}

	

}
