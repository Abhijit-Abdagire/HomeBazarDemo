package com.homeBazarTestCases;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.homeBazar.Utilities.HomeBazarReporting;

/*  
 * Author -       Abhijit Abdagire
 * Creation Date- 19-09-2022
 *  */

@Listeners(HomeBazarReporting.class)
public class TC_01_ImageComparison extends HomeBazarBaseClass {
	
	@Test
	public void verifyLogo() throws IOException {
		
	
		HomeBazarBaseClass.compareImages("//img[@title='banner Images']", "home-banner-img.png", "capturedElement.png");
		
		
	}

}
