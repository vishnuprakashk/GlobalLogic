package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.test.AppiumTest;

public class TimerHome {

	AppiumTest appiumTest;
	
	@AndroidFindBy(accessibility = "Timer")
	private MobileElement navBarTimer;
	@AndroidFindBy(accessibility = "timer_setup_digit_1")
	private MobileElement clikcOne;
	@AndroidFindBy(id = "action_bar_title")
	private MobileElement BarTitle;

	public TimerHome() {
		appiumTest = new AppiumTest();
		PageFactory.initElements(new AppiumFieldDecorator(appiumTest.getDriver()), this);
	}

	public TimerHome validateTitle(String titleText) {
		appiumTest.getAttribute(BarTitle, titleText);
		return this;
	}

	public TimerHome NavToTimer() {
		appiumTest.click(navBarTimer);
		return this;
	}

	public TimerHome ClickNumbers(String value) {
		appiumTest.getDriver().findElement(By.id("timer_setup_digit_"+value)).click();
		return this;
	}

}
