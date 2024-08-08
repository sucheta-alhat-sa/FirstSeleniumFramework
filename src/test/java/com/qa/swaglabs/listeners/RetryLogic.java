package com.qa.swaglabs.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryLogic implements IRetryAnalyzer{
	int count = 0;
	int maxTry = 2;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
