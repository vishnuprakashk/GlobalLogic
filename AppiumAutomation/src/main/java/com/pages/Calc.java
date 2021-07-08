package com.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobile.test.AppiumTest;

public class Calc extends AppiumTest {
	
	@AndroidFindBy (accessibility = "") private MobileElement key1;
	@AndroidFindBy (accessibility = "") private MobileElement key2;
	@AndroidFindBy (accessibility = "") private MobileElement key3;

}
