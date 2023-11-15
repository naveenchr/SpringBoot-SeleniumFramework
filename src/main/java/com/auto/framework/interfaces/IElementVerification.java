package com.auto.framework.interfaces;

import org.openqa.selenium.By;

/************************************************************************************************************************
 * @Date : Sep. 25, 2023
 * @Author : naveenchr
 * @Description : Element verification methods
 * @Version : 1.0
 ************************************************************************************************************************/
public interface IElementVerification {
	public String getTitle();

	public String getText(By by);

}
