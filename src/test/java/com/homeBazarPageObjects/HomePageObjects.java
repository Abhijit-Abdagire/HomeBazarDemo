package com.homeBazarPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePageObjects {
	WebDriver ldriver;
	

public HomePageObjects(WebDriver rdriver) {
	ldriver = rdriver;
	PageFactory.initElements(rdriver, this);
	
}

@FindBy(xpath="//div[contains(@class,'headerDropdown')]")
WebElement dropdown;

public void clickDropdown() {
	dropdown.click();
	
}

}
