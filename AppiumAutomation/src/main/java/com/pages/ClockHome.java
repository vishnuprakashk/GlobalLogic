package com.pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.test.AppiumTest;

public class ClockHome {

	AppiumTest appiumTest;
	
	@AndroidFindBy(accessibility = "Clock")
	private MobileElement navBarClock;
	@AndroidFindBy(accessibility = "Alarm")
	private MobileElement navBarAlarm;
	@AndroidFindBy(id = "action_bar_title")
	private MobileElement BarTitle;

	public ClockHome() {
		appiumTest = new AppiumTest();
		PageFactory.initElements(new AppiumFieldDecorator(appiumTest.getDriver()), this);
	}

	public ClockHome validateTitle(String titleText) {
		appiumTest.getAttribute(BarTitle, titleText);
		return this;
	}

	public ClockHome NavToClock() {
		appiumTest.click(navBarClock);
		return this;
	}

	public AlarmHome NavToAlarm() {
		appiumTest.click(navBarAlarm);
		return new AlarmHome();
	}

}
