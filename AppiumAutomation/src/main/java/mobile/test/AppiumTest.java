package mobile.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppiumTest {

	protected static AppiumDriver driver;
	protected static Properties props;
	InputStream propsInputStream;

	TestUtils testUtils;
	String exeutionTimeStamp = testUtils.getTimeStamp();

	public static HashMap<String, String> xmlStrings = new HashMap<String, String>();
	public InputStream xmlStringStream;

	public void setDriver(AppiumDriver driver) {
		this.driver = driver;
	}

	public AppiumDriver getDriver() {
		return driver;
	}

	public String captureScreenshot(String fileName) {
		File ssFile = driver.getScreenshotAs(OutputType.FILE);
		String imgPath = "Screenshots" + File.separator + exeutionTimeStamp + File.separator + fileName + "_"
				+ testUtils.getTimeStamp() + ".png";
		String completePath = System.getProperty("user.dir") + File.separator + imgPath;
		try {
			FileUtils.copyFile(ssFile, new File(imgPath));
			Reporter.log(
					"<a href='" + completePath + "'> <img src='" + completePath + "' height='200' width='160'/></a>");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return imgPath;
	}

	public void createDriverSession(String platformName, String automationName, String platformVersion)
			throws InterruptedException, Exception {
		try {

			// Read properties file for config
			String propertyFile = "config.properties";
			props = new Properties();
			propsInputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);
			props.load(propsInputStream);
			System.out.println("url: " + props.getProperty("AppiumURL"));

			// Read XML File for Expected Data
			String xmlFileName = "data/expectedStrings.xml";
			xmlStringStream = getClass().getClassLoader().getResourceAsStream(xmlFileName);
			testUtils = new TestUtils();
			xmlStrings = testUtils.parseStringXML(xmlStringStream);

			// Create capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", platformName);
			caps.setCapability("platformVersion", platformVersion);
			caps.setCapability("automationName", automationName);
			caps.setCapability("deviceName", props.getProperty("AndroidDeviceName")); // 92MAY02F57");
			caps.setCapability("appPackage", props.getProperty("AndroidAlarmAppPackage"));
			caps.setCapability("appActivity", props.getProperty("AndroidAlarmAppActivity"));
			caps.setCapability("newCommandTimeout", 300);
			URL url = new URL(props.getProperty("AppiumURL"));

			// Create Driver
			driver = new AndroidDriver(url, caps);
			Thread.sleep(3000);
			System.out.println("*********************** Creating Driver Session ***********************");
			String sessionID = driver.getSessionId().toString();
			System.out.println("Session ID: " + sessionID);
			captureScreenshot("AppLaunched");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (xmlStringStream != null) {
				xmlStringStream.close();
			}
			if (propsInputStream != null) {
				propsInputStream.close();
			}
		}
	}

	public void waitForVisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}

	public void click(MobileElement e) {
		waitForVisibility(e);
		e.click();
		Reporter.log("Clicking on: " + e.toString());
	}

	public void setValue(MobileElement e, String value) {
		waitForVisibility(e);
		e.setValue(value);
	}

	public void sendkeys(MobileElement e, String stringToType) {
		waitForVisibility(e);
		e.sendKeys(stringToType);
	}

	public void typekeys(MobileElement e, String stringToType) {
		waitForVisibility(e);
		e.setValue(stringToType);
	}

	public String getAttribute(MobileElement e, String attribName) {
		waitForVisibility(e);
		Reporter.log("Get " + attribName + " - Attribute from " + e.toString());
		return e.getAttribute(attribName);
	}

	public void quitDriver() {
		driver.quit();
	}

	public void closeApp() {
		((InteractsWithApps) driver).closeApp();
		System.out.println("Close Application");
	}

	public void launchApp() {
		((InteractsWithApps) driver).launchApp();
		System.out.println("Launch Application");
	}

}
