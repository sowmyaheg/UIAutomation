package com.webapp.uiautomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.webapp.uiautomation.WebdriverLibrary;

public class WebdriverAPIPage {

	private static String searchBar = "//div[@class='container']//input[@name='search']";
 	private static String apiNavBar = "//nav[@class='apinav']//div[@class='commands action active']//a[not(contains(@style, 'display: none'))]";
 	private static String clearButton = "//div[@class='container']//a[@class='icon icon-remove']";
 	private static String emptySearchbar = "//div[@class='container']//input[@placeholder='Search...']";
 	private static String XbuttonPresent = "//div[@class='container']//a[contains(@class,'icon icon-remove') and contains(@style, 'display: inline')]";
// 	private static String XbuttonHiding = "//div[@class='container']//a[contains(@class, 'icon icon-remove') and (text(),'clear')]";
 	private static String XbuttonHiding = "//div[@class='container']//a[contains(@class,'icon icon-remove') and (not(contains(@style, 'display: inline')))]";
 	
 	
  	private static WebdriverLibrary webdriverLibrary = WebdriverLibrary.getInstance();
	
	public static void searchApiElement(String string) {
		// TODO Auto-generated method stub
		
		webdriverLibrary.getElement(searchBar).click();
		webdriverLibrary.sendText(string);
	}

	public static void listApiResults(String string) {
		webdriverLibrary.listSearchElements(apiNavBar);
	}

	public static void verifyApiResults(List<String> string) {
		// TODO Auto-generated method stub
		webdriverLibrary.verifyResults(string);
	}

	public static void clearSearch() {
		// TODO Auto-generated method stub
		webdriverLibrary.getElement(clearButton).click();
		try{
			Thread.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		//check if X button was successfully clicked to return the empty search bar
		String placeHolder=webdriverLibrary.getElement(emptySearchbar).getAttribute("placeholder");
		System.out.println("Empty SearchBar value is: "+placeHolder);
	}
	
	public static void checkSearchbarStatus(String flagEmpty) {
		switch(flagEmpty){
		case "isEmpty":
			//Search bar is expected to be empty and no X button present
			webdriverLibrary.getElement(emptySearchbar);
			try{
				Thread.sleep(1000);
			} catch(Exception e) {
				e.printStackTrace();
			}
//			webdriverLibrary.getElement(XbuttonHiding);
			break;
		case "notEmpty":
			//X button should be present
			webdriverLibrary.getElement(XbuttonPresent);
			
		}
	}
}
