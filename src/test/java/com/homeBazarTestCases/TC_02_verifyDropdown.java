package com.homeBazarTestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import com.homeBazar.Utilities.HomeBazarReporting;
import com.homeBazarPageObjects.HomePageObjects;

/*  
 * Author -       Abhijit Abdagire
 * Creation Date- 19-09-2022
 *  */

@Listeners(HomeBazarReporting.class)
public class TC_02_verifyDropdown extends HomeBazarBaseClass{
	//HomePageObjects obj = new HomePageObjects(driver);

	@Test
	public void verifyDropdownLengthFailed() {
	HomePageObjects obj = new HomePageObjects(driver);
		obj.clickDropdown();
		List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@class,'headerDropdown')]"));
		int length = elements.size();
		boolean result = length==6;
		Assert.assertEquals(result, false);
		
	}
	@Test (priority=1)
	public void testDropdownOptions() {
		HomePageObjects obj = new HomePageObjects(driver);
		obj.clickDropdown();
		List<WebElement> elements = driver.findElements(By.xpath("//li[contains(@class,'headerDropdown')]"));
		
		for(WebElement element:elements) {
			
			if(element.getText().equalsIgnoreCase("Pune")) {
				element.click();
				break;
				
			}
			
			
		}
		
	}

}
