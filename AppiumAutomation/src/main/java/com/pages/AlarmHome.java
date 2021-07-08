package com.pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.test.AppiumTest;

public class AlarmHome {

	AppiumTest appiumTest;

	public AlarmHome() {
		appiumTest = new AppiumTest();
		PageFactory.initElements(new AppiumFieldDecorator(appiumTest.getDriver()), this);
	}

	@AndroidFindBy(accessibility = "Alarm")
	private MobileElement navBarAlarm;

	@AndroidFindBy(id = "action_bar_title")
	private MobileElement BarTitle;

	@AndroidFindBy(accessibility = "Add alarm")
	private MobileElement addAlarm;

	@AndroidFindBy(id = "android:id/hours")
	private MobileElement hours;

	@AndroidFindBy(id = "android:id/minutes")
	private MobileElement minutes;

	@AndroidFindBy(id = "android:id/button1")
	private MobileElement okBtn;

	@AndroidFindBy(id = "android:id/button2")
	private MobileElement cancelBtn;

	@AndroidFindBy(accessibility = "No label specified")
	private MobileElement editLable;

	@AndroidFindBy(id = "textinput_placeholder")
	private MobileElement LableTxtBx;

	public AlarmHome cilckAddAlarm() {
		appiumTest.click(addAlarm);
		return this;
	}

	public String getTitle() {
		return appiumTest.getAttribute(BarTitle, "text");
	}

	public AlarmHome clickOk() {
		appiumTest.click(okBtn);
		return this;
	}

	public AlarmHome clickCancel() {
		appiumTest.click(cancelBtn);
		return this;
	}

	public AlarmHome NavToAlarm() {
		appiumTest.click(navBarAlarm);
		return this;
	}

	public AlarmHome setHour(String value) {
		appiumTest.click(hours);
		return this;
	}

	public AlarmHome selectTime(String value) {
		System.out.println("Setting time: " + value);
		appiumTest.click((MobileElement) appiumTest.getDriver().findElement(MobileBy.AccessibilityId(value)));
		return this;
	}

	public AlarmHome EditLable(String value) {
		appiumTest.click(editLable);
		appiumTest.typekeys(LableTxtBx, value);
		return this;
	}

}
