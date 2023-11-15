package com.auto.framework.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.TestResult;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Date : Sep. 25, 2023
 * @Author : naveenchr
 * @Description : Test listener implementation to manage driver and log results
 * @Version : 1.0
 ************************************************************************************************************************/
@Slf4j
@Component
@Scope("driverscope")
public class TestListener extends TestListenerAdapter {

	@Override
	public void onTestStart(ITestResult iTestResult) {
		super.onTestStart(iTestResult);
		log.info("Started: {}", iTestResult.getName());
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		super.onTestSuccess(iTestResult);
		testReportUpdate(iTestResult);
		log.info("Finished successfully: {}", iTestResult.getName());
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		super.onTestSkipped(iTestResult);
		testReportUpdate(iTestResult);
		log.info("Skipped: {}", iTestResult.getName());
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		super.onTestFailure(iTestResult);
		log.error("Failed: {} with: {}", iTestResult.getName(), iTestResult.getThrowable().toString());
		testReportUpdate(iTestResult);
	}

	@Attachment(value = "Screen shot", type = "image/png", fileExtension = ".png")
	private byte[] attachScreenShot(WebDriver driver) {
		try {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		} catch (WebDriverException e) {
			log.error("Selenium screenshot capture failed: {}", e.getMessage());
		}
		return new byte[0];
	}

	public void testReportUpdate(ITestResult iTestResult) {
		String testSetNumber = iTestResult.getName() + " " + (((TestResult) iTestResult).getParameterIndex() + 1);

		log.info("Allure report : {}", iTestResult);
		log.info("Test Set Number : {}", testSetNumber);
		AllureLifecycle lifecycle = Allure.getLifecycle();
		if (lifecycle.getCurrentTestCase().isPresent()) {
			lifecycle.updateTestCase(testResult -> testResult.setName(testSetNumber));
		}
	}

}
