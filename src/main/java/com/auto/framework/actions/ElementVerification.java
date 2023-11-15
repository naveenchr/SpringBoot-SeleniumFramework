package com.auto.framework.actions;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.auto.framework.interfaces.IElementVerification;

import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Date : Sep. 25, 2023
 * @Author : naveenchr
 * @Description :Element verification interface implementation
 * @Version : 1.0
 ************************************************************************************************************************/

@Slf4j
@Component
public class ElementVerification extends ActionsBaseClass implements IElementVerification {

	@Override
	public String getTitle() {
		String title = driver.getTitle();
		log.debug("Opened Page : {}", title);
		return title;
	}
	
	@Override
	public String getText(By by) {
		String text = driver.findElement(by).getText();
		log.debug("Text Value: {}", text);
		return text;
	}


}
