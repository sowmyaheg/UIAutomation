package com.webapp.uiautomation;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class WebdriverAPITest {

	public static String webLink = "http://webdriver.io";
	public static String navMenu = "API";
	public static String searchText = "click";
	List<String> expectedResults = Arrays.asList("click", "doubleClick", "leftClick", "middleClick", "rightClick");
	List<String> negExpectedResults = Arrays.asList("click", "leftClick", "middleClick", "rightClick");
	
	@BeforeTest
	public void initDriver() {
		WebdriverIOPage.initDriver();
	}
	@AfterTest
	public void teardown() {
		WebdriverIOPage.teardown();
	}
	
	//UI end to end user test class
	@Test
	public void testlaunchWebdriverIOE2E(){
		WebdriverIOPage.launchPage(webLink);
		WebdriverIOPage.navigateTo(navMenu);
		WebdriverAPIPage.searchApiElement(searchText);
		WebdriverAPIPage.listApiResults(searchText);
		WebdriverAPIPage.verifyApiResults(expectedResults);
	}
	
	//Below are the test cases for functionality of API search widget
	@Test
	public void testClearSearch() {
		WebdriverIOPage.launchPage(webLink);
		WebdriverIOPage.navigateTo(navMenu);
		WebdriverAPIPage.searchApiElement(searchText);
		WebdriverAPIPage.clearSearch();
	}
	
	@Test
	public void testSearchCaseSensitivity() {
		//Check if search results returned are case sensitive 
		WebdriverIOPage.launchPage(webLink);
		WebdriverIOPage.navigateTo(navMenu);
		//Checking to see if results are case insensitive
		WebdriverAPIPage.searchApiElement(searchText.toUpperCase());
		WebdriverAPIPage.listApiResults(searchText);
		WebdriverAPIPage.verifyApiResults(expectedResults);
	}
	
	//Negative test case, expected to fail
	@Test
	public void testSearchMissingResults() {
		//Check if there are mismatch in returned results and expected
		WebdriverIOPage.launchPage(webLink);
		WebdriverIOPage.navigateTo(navMenu);
		WebdriverAPIPage.searchApiElement(searchText);
		WebdriverAPIPage.listApiResults(searchText);
		WebdriverAPIPage.verifyApiResults(negExpectedResults);
	}
	
	@Test
	public void testSearchbarPlaceholder() {
		WebdriverIOPage.launchPage(webLink);
		WebdriverIOPage.navigateTo(navMenu);
		WebdriverAPIPage.checkSearchbarStatus("isEmpty");
		
	}
	
	@Test
	public void testXbuttonApprearance() {
		//X button should appear only when there is any text entered in search bar
		WebdriverIOPage.launchPage(webLink);
		WebdriverIOPage.navigateTo(navMenu);
		WebdriverAPIPage.searchApiElement(searchText);
		WebdriverAPIPage.checkSearchbarStatus("notEmpty");
	}
}
