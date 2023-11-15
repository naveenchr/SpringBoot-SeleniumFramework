package com.auto.framework.pageobjects.demoqa;

import static com.auto.framework.constants.Constants.RADIOBUTTON_PAGE;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.auto.framework.pageobjects.common.BasePage;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

/************************************************************************************************************************
 * @Date : Oct. 23, 2023
 * @Author : naveenchr
 * @Description : Page object fragment for Radio button
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
@AllArgsConstructor
public class RadioButtonPF extends BasePage {

	private static By radioButtons = By.xpath("//*[@class='custom-control custom-radio custom-control-inline']");
	private static By successMsg = By.className("text-success");

	public void openRadioButtonPage() {
		iUIElements.openURL(myProperties.getDemoUrl() + RADIOBUTTON_PAGE);
	}

	@Step("Clicked on the Radio Button")
	public void clickRadioButton(String text) {
		iUIElements.searchAndClickByText(radioButtons, text);
	}

	public String getConfirmationMessage() {
		return iElementVerification.getText(successMsg);
	}

}
