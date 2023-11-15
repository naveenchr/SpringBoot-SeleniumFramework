package com.auto.framework.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.auto.framework.config.MyProperties;

/************************************************************************************************************************
 * @Date : Sep. 25, 2023
 * @Author : naveenchr
 * @Description : Base class for Actions
 * @Version : 1.0
 ************************************************************************************************************************/

@Component
public class ActionsBaseClass {

	@Autowired
	@Lazy // to start browser only on script execution
	public WebDriver driver;

	@Autowired
	@Lazy
	public WebDriverWait webDriverWait;

	@Autowired
	public ApplicationContext applicationContext;

	@Autowired
	public MyProperties myProperties;
	
	@Autowired
	public UtilityClass utilityClass;

}
