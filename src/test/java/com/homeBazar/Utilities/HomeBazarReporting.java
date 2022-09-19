package com.homeBazar.Utilities;


import org.testng.ITestListener;
import org.testng.ITestResult;

import com.homeBazarTestCases.HomeBazarBaseClass;


public class HomeBazarReporting extends HomeBazarBaseClass implements ITestListener{
	
	public void onTestFailure(ITestResult result) {
		
		takeScreenshot(result.getMethod().getMethodName());
		
		
		
	}

	
	
	
	
}
