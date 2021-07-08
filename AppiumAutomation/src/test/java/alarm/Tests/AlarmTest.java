package alarm.Tests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.AlarmHome;
import com.pages.ClockHome;
import com.pages.TimerHome;
import com.utils.TestUtils;

import mobile.test.AppiumTest;

public class AlarmTest {
	static ClockHome clockHome;
	static AlarmHome alarmHome;
	static AppiumTest appiumTest;
	static TimerHome timerHome;
	InputStream jsonDataIs;
	JSONObject alarmData;
	TestUtils testUtils;

	@Parameters({ "platformName", "automationName", "platformVersion" })
	@BeforeClass
	public void beforeClass(String platformName, String automationName, String platformVersion)
			throws InterruptedException, Exception {
		try {
			appiumTest = new AppiumTest();
			appiumTest.createDriverSession(platformName, automationName, platformVersion);
			// Read input data from JSON
			String dataFileName = "data/alarmInputData.json";
			jsonDataIs = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(jsonDataIs);
			alarmData = new JSONObject(tokener);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (jsonDataIs != null) {
				jsonDataIs.close();
			}
		}

	}

	@AfterClass
	public void afterClass() {
		appiumTest.quitDriver();
	}

	@BeforeMethod
	public void beforeMethod(Method methodName) {
		clockHome = new ClockHome();
		alarmHome = new AlarmHome();
		System.out.println("\n" + "*** Executing Method : " + methodName);
		appiumTest.closeApp();
		appiumTest.launchApp();
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Test
	public void cancelAlarm() throws Exception {
		navigateToAlarmPage();
		alarmHome.cilckAddAlarm();
		alarmHome.clickCancel();
		try {
			Thread.sleep(3000); // Just to see the final action before it close
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void setAlarm() {
		navigateToAlarmPage();
		// Set Alarm
		alarmHome.cilckAddAlarm();
		alarmHome.selectTime(alarmData.getJSONObject("validTime").getString("hours"));
		alarmHome.selectTime(alarmData.getJSONObject("validTime").getString("minutes"));
		alarmHome.clickOk();
		try {
			Thread.sleep(3000); // Just to see the final action before it close
		} catch (Exception e) {
			e.printStackTrace();
		}
		appiumTest.captureScreenshot("AlarmSet");
	}

	@Test
	public void setTimer() {
		String title;
		String expectedTitle;
		timerHome = new TimerHome();
		timerHome.NavToTimer();
		title = alarmHome.getTitle();
		expectedTitle = appiumTest.xmlStrings.get("TitileOfTimer");
		System.out.println("Actual Title: " + title + ",Expected Title: " + expectedTitle);
		Assert.assertEquals(title, expectedTitle);
		appiumTest.captureScreenshot("TimerHome");
		timerHome.ClickNumbers("1");
		timerHome.ClickNumbers("2");
		timerHome.ClickNumbers("3");
		timerHome.ClickNumbers("4");
		timerHome.ClickNumbers("5");
		timerHome.ClickNumbers("6");
		smartWait();
		appiumTest.captureScreenshot("AfterTimerSet");

	}

	public void smartWait() {
		try {
			Thread.sleep(3000); // Just to see the final action before it close
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigateToAlarmPage() {
		String title;
		String expectedTitle;
		// Clock Home Page
		title = alarmHome.getTitle();
		expectedTitle = appiumTest.xmlStrings.get("TitileOfClock");
		System.out.println("Actual Title: " + title + ",Expected Title: " + expectedTitle);
		Assert.assertEquals(title, expectedTitle);
		// Alarm Home Page
		clockHome.NavToAlarm();
		appiumTest.captureScreenshot("Navg2Alarm");
		title = alarmHome.getTitle();
		expectedTitle = appiumTest.xmlStrings.get("TitileOfAlarm");
		System.out.println("Actual Title: " + title + ",Expected Title: " + expectedTitle);
		Assert.assertEquals(title, expectedTitle);
	}

}
