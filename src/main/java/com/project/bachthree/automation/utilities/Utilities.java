package com.project.bachthree.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import com.project.bachthree.automation.qa.base.BaseClass;

public class Utilities extends BaseClass{
	
	
	public static Properties prop;
	public static FileInputStream input;
	public static String globalFilePath = "./src/main/java/configs/config.properties";
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static long EXPLICIT_WAIT_TIME = 15;
	public static final long QUICK_THREAD_WAIT_TIME = 2000;
	public static String chrome = "chrome";
	public static String firefox = "firefox";
	public static String RUNNING_ON_CHROME = "\n" + "== RUNNING ON CHROME BROWSER ==" + "\n";
	public static String RUNNING_ON_FIREFOX = "\n" + "== RUNNING ON FIREFOX BROWSER ==" + "\n";
	public static String CLOSNG_BROWSER_SESSION = "\n" + "== BROWSER CLOSED SUCCESSFULLY ==" + "\n";
	
	static List<WebElement> checkboxesElement;
	
	
	public static void initGlobalConfiguration() {
		
		prop = new Properties();
		try {
			input = new FileInputStream(globalFilePath);
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getBrowserName() {
		
		String browserName = prop.getProperty("browser");
		return browserName;
	}
	
	
	public static String getBaseUrl() {
		
		String baseUrl = prop.getProperty("baseurl");
		return baseUrl;
	}
	
	
	public static void clickAllCheckBoxes() {
		
		Reporter.log("Clicking all checkboxes in this page", true);

		for(WebElement singleCheckBox: checkboxesElement ) {
			
			singleCheckBox.click();
		}
	}
	
public static void takeScreenshotAtEndOfTest() throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver; 
		File source = ts.getScreenshotAs(OutputType.FILE); 
		File destination = new File("./src/test/resources"+ "/screenshots/" + System.currentTimeMillis() + ".png"); 
		FileHandler.copy(source, destination);
	}

}
