package com.auto.framework.pageobjects.demoqa;

import static com.auto.framework.constants.Constants.CHECKBOX_PAGE;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.auto.framework.pageobjects.common.BasePage;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

/************************************************************************************************************************
 * @Date : Nov. 14, 2023
 * @Author : nachrist
 * @Description : Page fragment class for Checkbox interactions 
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
@AllArgsConstructor
public class CheckBoxPF extends BasePage {

	private static By level1Menu = By.xpath("//*[@id='tree-node']/ol/li/span/label/span[3]");
	private static By level2Menu = By.xpath("//*[@id='tree-node']/ol/li/ol/li/span/label/span[3]");
	private static By level3Menu = By.xpath("//*[@id='tree-node']/ol/li/ol/li/ol/li/span/label/span[3]");
	private static By level4Menu = By.xpath("//*[@id='tree-node']/ol/li/ol/li/ol/li/ol/li/span/label/span[3]");
	private static By expandButtonRL = By.xpath("//button[@title='Toggle']");
	private static By successMsg = By.className("text-success");

	public void openCheckBoxPage() {
		iUIElements.openURL(myProperties.getDemoUrl() + CHECKBOX_PAGE);
	}

	@Step("Expand Level 1 Menu")
	public void expandLevel1Menu() {
		iUIElements.clickRelativeLeftElement(level1Menu, expandButtonRL);
	}

	@Step("Expand Level 2 Menu")
	public void expandLevel2Menu(String textForSearch) {
		iUIElements.searchAndClickRelativeLeftElement(level2Menu, expandButtonRL, textForSearch);
	}

	@Step("Expand Level 3 Menu")
	public void expandLevel3Menu(String textForSearch) {
		iUIElements.searchAndClickRelativeLeftElement(level3Menu, expandButtonRL, textForSearch);
	}

	@Step("Select Level 4 Option")
	public void clickLevel4Option(String textForSearch) {
		iUIElements.searchAndClickByText(level4Menu, textForSearch);
	}

	public String getConfirmationMessage() {
		return iElementVerification.getText(successMsg);
	}

}
