package com.auto.framework.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import com.auto.framework.interfaces.IExplicitWait;

/************************************************************************************************************************
 * @Date : Sep. 25, 2023
 * @Author : naveenchr
 * @Description : Explicit wait implementation class
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
public class ExplicitWait extends ActionsBaseClass implements IExplicitWait {

	@Override
	public WebElement waitForElementToBeClickable(By by) {
		return webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
	}

}
