package com.auto.framework.pageobjects.demoqa;

import static com.auto.framework.constants.Constants.WEBTABLES_PAGE;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.auto.framework.pageobjects.common.BasePage;
import com.auto.framework.testdata.UserModal;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

/************************************************************************************************************************
 * @Date : Oct. 23, 2023
 * @Author : naveenchr
 * @Description : Page object fragment for Web Table
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
@AllArgsConstructor
public class WebTablePF extends BasePage {

	private static By addButton = By.cssSelector("#addNewRecordButton");
	private static By firstNameTextField = By.cssSelector("#firstName");
	private static By lastNameTextField = By.id("lastName");
	private static By emailTextField = By.xpath("//*[@id='userEmail']");
	private static By ageTextField = By.cssSelector("#age");
	private static By salaryTextField = By.id("salary");
	private static By departmentTextField = By.id("department");

	public void openWebTablesPage() {
		iUIElements.openURL(myProperties.getDemoUrl() + WEBTABLES_PAGE);
	}

	@Step("Add User Data")
	public void addUserData(UserModal userData) {
		iUIElements.click(addButton);
		iUIElements.sendKeys(firstNameTextField, userData.getFirstName());
		iUIElements.sendKeys(lastNameTextField, userData.getLastName());
		iUIElements.sendKeys(emailTextField, userData.getEmail());
		iUIElements.sendKeys(ageTextField, userData.getAge());
		iUIElements.sendKeys(salaryTextField, userData.getSalary());
		iUIElements.sendKeys(departmentTextField, userData.getDepartment());
	}

//	public String getConfirmationMessage() {
////		return iElementVerification.getText(successMsg);
//	}

}
