package com.auto.framework.pageobjects.demoqa;

import static com.auto.framework.constants.Constants.TEXTBOX_PAGE;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.auto.framework.pageobjects.common.BasePage;
import com.auto.framework.testdata.UserModal;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Date : Oct. 20, 2023
 * @Author : naveenchr
 * @Description : Page object fragment for Text Box menu
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
@AllArgsConstructor
@Slf4j
public class TextBoxPF extends BasePage {

	private static By fullnameTF = By.id("userName");
	private static By emailTF = By.id("userEmail");
	private static By currentAddressTF = By.cssSelector("#currentAddress");
	private static By permanentAddressTF = By.xpath("//*[@id='permanentAddress']");
	private static By submitButton = By.cssSelector("#submit");

	private static By nameText = By.cssSelector("p#name");
	private static By emailText = By.cssSelector("p#email");
	private static By currAddText = By.cssSelector("p#currentAddress");
	private static By permAddText = By.cssSelector("p#permanentAddress");

	public void openTextBoxPage() {
		iUIElements.openURL(myProperties.getDemoUrl() + TEXTBOX_PAGE);
	}

	public void updateTextBoxes(UserModal userData) {
		enterFullname(userData.getFirstName());
		enterEmail(userData.getEmail());
		enterCurrentAddress(userData.getCurrAddress());
		enterPermanentAddress(userData.getPermAddress());
		submitForm();
	}

	@Step("Enter Fullname")
	public void enterFullname(String fullname) {
		iUIElements.sendKeys(fullnameTF, fullname);
	}

	@Step("Enter Email")
	public void enterEmail(String email) {
		iUIElements.sendKeys(emailTF, email);
	}

	@Step("Enter Current Address")
	public void enterCurrentAddress(String currAddress) {
		iUIElements.sendKeys(currentAddressTF, currAddress);
	}

	@Step("Enter Permanent Address")
	public void enterPermanentAddress(String permAddress) {
		iUIElements.sendKeys(permanentAddressTF, permAddress);
	}

	@Step("Submit form")
	public void submitForm() {
		iUIElements.click(submitButton);
	}

	public UserModal getConfirmationMessage() {
		UserModal userModal = UserModal.builder().firstName(iElementVerification.getText(nameText).split(":")[1])
				.email(iElementVerification.getText(emailText).split(":")[1])
				.currAddress(iElementVerification.getText(currAddText).split(":")[1])
				.permAddress(iElementVerification.getText(permAddText).split(":")[1]).build();
		log.info("Confirmation Data: {}", userModal);
		return userModal;
	}

}
