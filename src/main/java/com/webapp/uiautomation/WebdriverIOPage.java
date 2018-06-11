package com.webapp.uiautomation;

import static org.testng.AssertJUnit.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverIOPage {
	
	private static String apiNavLink = "//nav[@class='mainnav']//a[@href='/api.html']";
	private static String homeNavLink = "//*[@class='mainnav']/a[@href='/']";
	private static String devNavLink = "//*[@class='mainnav']/a[@href='/guide.html']";
	private static String conNavLink = "//*[@class='mainnav']/a[@href='/contribute.html']";
	
	private static WebdriverLibrary webdriverLibrary = WebdriverLibrary.getInstance();
	
	public static String chooseMenuOption(String pageName) {
		String pageLink=null;
		
		switch(pageName.toLowerCase().replaceAll("\\s", "")){
		case "home":
			pageLink = homeNavLink;
			break;
		case "developer guide":
			pageLink = devNavLink;
			break;
		case "api":
			pageLink = apiNavLink;
			break;
		case "contribute":
			pageLink = conNavLink;
			break;			
		}
		return pageLink;
	}
	
	public static void navigateTo(String string) {
		// TODO Auto-generated method stub

		String locator = chooseMenuOption(string);
		webdriverLibrary.getElement(locator).click();
		
	}

	public static void launchPage(String string) {
		// TODO Auto-generated method stub
		webdriverLibrary.launchPage(string);
	}

	public static void teardown() {
		// TODO Auto-generated method stub
		webdriverLibrary.quitDriver();
	}

	public static void initDriver() {
		// TODO Auto-generated method stub
		webdriverLibrary.initDriver();
	}



}
