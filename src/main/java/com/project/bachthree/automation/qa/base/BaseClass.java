package com.project.bachthree.automation.qa.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.project.bachthree.automation.utilities.Utilities;
import com.project.bachthree.automation.utilities.WebEventListener;

public class BaseClass {
	
	public static WebDriver driver;
	public static WebDriver eventDriver;
	public static com.project.bachthree.automation.utilities.WebEventListener listener;
	public static EventFiringDecorator<WebDriver> decorator;
	
	//@BeforeMethod
	public void setup() {
		
		Utilities.initGlobalConfiguration();
		String browserName = Utilities.getBrowserName();
		if(browserName.equals("chrome")) {
			
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox")) {
			
			driver = new FirefoxDriver();
		}
		
		else if(browserName.equals("edge")) {
			
			driver = new EdgeDriver();
		}
		
		else {
			
			Reporter.log("Incorrect browser selection.\n Therefore, loading default browser as chrome");
			driver = new ChromeDriver();
		}
		callEventListenerWebDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.get(Utilities.getBaseUrl());
		
	}
	
		@BeforeMethod
		@Parameters("browser")
		public void CrossBrowsersetup(String crossBrowser) {
		
		Utilities.initGlobalConfiguration();
		if(crossBrowser.equals("chrome")) {
			
			driver = new ChromeDriver();
		}
		else if(crossBrowser.equals("firefox")) {
			
			driver = new FirefoxDriver();
		}
		
		else if(crossBrowser.equals("edge")) {
			
			driver = new EdgeDriver();
		}
		
		else {
			
			Reporter.log("Incorrect browser selection.\n Therefore, loading default browser as chrome");
			driver = new ChromeDriver();
		}
		
		callEventListenerWebDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.get(Utilities.getBaseUrl());
		
	}
	
	
		 public static WebDriver callEventListenerWebDriver() {
			  
			  listener = new WebEventListener(); 
			  decorator = new EventFiringDecorator<WebDriver>(listener); 
			  eventDriver = decorator.decorate(driver); 
			  driver = eventDriver; 
			  return driver; 
			  }
	
	
	@AfterMethod
	public void closeBrowserSession(ITestResult result) throws IOException {
		
		if(ITestResult.FAILURE==result.getStatus()) {
			
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File("./src/test/resources"+"/screenshots/"+ System.currentTimeMillis()+".png");
			FileHandler.copy(source, destination);
		
		}
		
		driver.quit();
	}
	

}
