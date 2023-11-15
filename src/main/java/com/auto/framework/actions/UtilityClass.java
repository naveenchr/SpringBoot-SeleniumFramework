package com.auto.framework.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/************************************************************************************************************************
 * @Date : Oct. 20, 2023
 * @Author : naveenchr
 * @Description : Helper class for UI action classes
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
public class UtilityClass {

	public void scrollHelper(WebDriver driver, WebDriverWait webDriverWait, ApplicationContext applicationContext,
			By by) {
		webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
		((JavascriptExecutor) applicationContext.getBean(WebDriver.class))
				.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
	}

}
