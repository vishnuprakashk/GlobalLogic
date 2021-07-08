package com.listeners;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.reports.ExtentReport;

import mobile.test.AppiumTest;

public class TestNgListener implements ITestListener {

	AppiumTest appiumTest = new AppiumTest();

	public void onTestFailure(ITestResult result) {
		if (result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println("\n" + "Error occured - Printing stacktrace Below" + "\n" + sw.toString());
			Reporter.log("Exception at " + result.getName());
			String completePath = System.getProperty("user.dir") + File.separator
					+ appiumTest.captureScreenshot("Fail_" + result.getName());
			Reporter.log(
					"<a href='" + completePath + "'> <img src='" + completePath + "' height='200' width='160'/></a>");
		}
		ExtentReport.getTest().log(Status.FAIL, "Test Failed");
		ExtentReport.getReporter().flush();
	}

	public void onTestStart(ITestResult result) {
		ExtentReport.startTest(result.getName(), result.getMethod().getDescription()).assignAuthor("Vishnu");
	}

	public void onTestSuccess(ITestResult result) {
		ExtentReport.getTest().log(Status.PASS, "Test Passed");
		ExtentReport.getReporter().flush();
	}

	public void onTestSkipped(ITestResult result) {
		ExtentReport.getTest().log(Status.SKIP, "Test Skipped");
		ExtentReport.getReporter().flush();
	}
	
	public void onFinish(ITestResult result) {
		ExtentReport.getReporter().flush();
	}

}
