package com.reports;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	private static ExtentReports extent;
	final static String filepath = "Extent.html";
	static Map<Integer, ExtentTest> extentTestMap = new HashMap();

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			System.out.println("Createing ËxtentReport");
			ExtentSparkReporter spark = new ExtentSparkReporter("Extent.html");
			spark.config().setReportName("GlobalLogic-AppiumReport");
			spark.config().setTheme(Theme.STANDARD);
			extent = new ExtentReports();
			extent.attachReporter(spark);
		}
		return extent;
	}

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.removeTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {
		ExtentTest test = getReporter().createTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}
