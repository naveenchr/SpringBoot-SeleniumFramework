package com.auto.framework.interfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/************************************************************************************************************************
 * @Date : Sep. 25, 2023
 * @Author : naveenchr
 * @Description : Interface for UIElement actions
 * @Version : 1.0
 ************************************************************************************************************************/
public interface IUIElements {

	WebDriver getWebDriver();

	void sendKeys(By by, String keysToSend);

	void click(By by);

	void openURL(String url);

	void searchAndClickByText(By by, String textForSearch);

	void clickNestedMenus(By by, String tagName, List<String> menuList);

	void searchAndClickTableByText(By by, String textForSearch, String value);

	String findElementsbyIndex(By by, int index);

	void searchAndClickRelativeLeftElement(By toLeftoOfBy, By withBy, String textForSearch);

	void clickRelativeLeftElement(By toLeftoOfBy, By withBy);

}
