package com.auto.framework;

import static com.auto.framework.constants.Constants.APPLICATION_JSON;
import static com.auto.framework.constants.Constants.CONTENT_TYPE;
import static com.auto.framework.constants.Constants.TESTDATA_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.auto.framework.listeners.TestListener;
import com.auto.framework.pageobjects.common.BasePage;
import com.auto.framework.pageobjects.demoqa.ElementsPage;
import com.auto.framework.testdata.UserModal;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Date : Nov. 28, 2023
 * @Author : naveenchr
 * @Description : API tests integrated into UI tests sample
 * @Version : 1.0
 ************************************************************************************************************************/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Listeners(TestListener.class)
@Slf4j
public class APIIntegratedTests extends AbstractTestNGSpringContextTests {
	@Autowired
	private BasePage basePage;

	@Autowired
	public ElementsPage elementsPage;
	
	@Test
	public void apiTextBoxVal__TC005() {
		// Extract testdata from API
		Response response = given().baseUri(TESTDATA_URI).auth().none().log().all()
				.header(CONTENT_TYPE, APPLICATION_JSON).when().get().then().extract().response();
		log.info("Response Details : {}", response.getBody().as(UserModal.class));
		UserModal userData = response.getBody().as(UserModal.class);

		// Opens browser page
		elementsPage.textBoxPF.openTextBoxPage();

		// Perform testing actions
		elementsPage.textBoxPF.enterFullname(userData.getFirstName());
		elementsPage.textBoxPF.enterEmail(userData.getEmail());
		elementsPage.textBoxPF.enterCurrentAddress(userData.getCurrAddress());
		elementsPage.textBoxPF.enterPermanentAddress(userData.getPermAddress());
		elementsPage.textBoxPF.submitForm();

		// Assert data points
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getFirstName(), is(userData.getFirstName()));
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getEmail(), is(userData.getEmail()));
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getCurrAddress(), is(userData.getCurrAddress()));
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getPermAddress(), is(userData.getPermAddress()));

	}

	@BeforeMethod
	@Override
	public void springTestContextPrepareTestInstance() throws Exception {
		super.springTestContextPrepareTestInstance();
	}

	@AfterMethod(alwaysRun = true)
	public void teardownDriver() {
		basePage.teardownDriver();
	}

}
