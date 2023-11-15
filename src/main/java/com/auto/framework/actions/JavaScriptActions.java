package com.auto.framework.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import com.auto.framework.interfaces.IJavaScriptActions;

import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Date : Sep. 25, 2023
 * @Author : naveenchr
 * @Description : Impelementation class for interface JavaScriptActions
 * @Version : 1.0
 ************************************************************************************************************************/

@Slf4j
@Component
public class JavaScriptActions extends ActionsBaseClass implements IJavaScriptActions {

	@Override
	public void scrollIntoView(By by) {
		webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
		log.debug("Test {}", driver.getTitle());
		((JavascriptExecutor) applicationContext.getBean(WebDriver.class))
				.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));

		log.debug("Scrolled into View..");
	}

	@Override
	public void click(By by) {
		scrollIntoView(by);
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) applicationContext.getBean(WebDriver.class)).executeScript("arguments[0].click();",
				element);

		log.debug("Clicked on Link..");

	}
}
