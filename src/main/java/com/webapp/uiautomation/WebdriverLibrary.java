package com.webapp.uiautomation;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class WebdriverLibrary {

	private static WebdriverLibrary wLib = new WebdriverLibrary();
	public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	private static WebElement element = null;
	private static List<WebElement> elements = null;
	
	public static WebdriverLibrary getInstance() {
		return wLib;
	}
	
	private WebdriverLibrary() {
		
	}
	
	public static void initDriver() {
		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver","lib/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		threadDriver.set(driver);
		
	}
	
	public static void launchPage(String webLink) {
		// TODO Auto-generated method stub
//		startSelenium();
		WebDriver driver = threadDriver.get();
        driver.get(webLink);
        
        WebDriverWait wait= new WebDriverWait(driver, 10);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        System.out.println(driver.getTitle().toString());
//        assertTrue(driver.getTitle().contains("WebdriverIO - WebDriver bindings for Node.js"));
        threadDriver.set(driver);
	}
	
	public static WebElement getElement(String locator){
		
		WebDriver driver = threadDriver.get();
		int numOfAttemps =0;
		boolean isFound =false;
		do{
			numOfAttemps++;
			try{
				if (numOfAttemps == 10)
					break;
				
				if(driver.findElement(By.xpath(locator)).isDisplayed() && driver.findElement(By.xpath(locator)).isEnabled()==true){
					element = driver.findElement(By.xpath(locator));

					isFound=true;
					break;
				}
				Thread.sleep(1000);	
			}catch(Exception e){
				System.out.println(locator+":Element not found");
			}
		} while(!isFound || numOfAttemps<10);
		System.out.println("numofAttemps:"+numOfAttemps+":"+ locator +":isFound:"+isFound);
		return element;
	}
	
	public void sendText(String text) {
		try {
			if(element != null){
				element.clear();
				element.sendKeys(text);
			}
		} catch(Exception e) {
			System.out.println("element was not clicked");
		}
		
	}

	public void verifyResults(List<String> expectedResults) {
		// TODO Auto-generated method stub
		List<String> returnedResults = new ArrayList<String>();
		
		try {
			if(elements!= null){
				for(WebElement ele: elements){
					returnedResults.add(ele.getText());
				}
			}
			if(expectedResults.containsAll(returnedResults) && returnedResults.containsAll(expectedResults)){
				System.out.println("All elements present");
			} else {
				throw new Exception();
			}
		} catch(Exception e){
//			System.out.println("Elements are not equal");
			Assert.fail("Elements are not equal");
		}
	}

	public List<WebElement> getElements(String locator) {
		// TODO Auto-generated method stub
		WebDriver driver = threadDriver.get();
//		List<WebElement> elements = null;
		
		int numOfAttemps =0;
		boolean isFound =false;
		do{
			numOfAttemps++;
			try{
				if (numOfAttemps == 10)
					break;
				
				if(driver.findElement(By.xpath(locator)).isDisplayed() && driver.findElement(By.xpath(locator)).isEnabled()==true){
					elements = driver.findElements(By.xpath(locator));

					isFound=true;
					break;
				}
				Thread.sleep(1000);	
			}catch(Exception e){
				System.out.println(locator+":Element not found");
			}
		} while(!isFound || numOfAttemps<10);
		System.out.println("numofAttemps:"+numOfAttemps+":"+ locator +":isFound:"+isFound);
				
		return elements;
	}

	public void listSearchElements(String apiNavBar) {
		// TODO Auto-generated method stub

		elements = getElements(apiNavBar);
		System.out.println("Results returned from Search are:");
		for (WebElement ele : elements) {
			String links = ele.getText();
			System.out.println(links);
		}
		
	}

	public void quitDriver() {
		// TODO Auto-generated method stub
		WebDriver driver = threadDriver.get();
		if(driver!=null){
			driver.close();
		}
		threadDriver.remove();
	}
	
}
