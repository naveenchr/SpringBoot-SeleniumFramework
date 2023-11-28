package com.auto.framework;

import static com.auto.framework.constants.Constants.APPLICATION_JSON;
import static com.auto.framework.constants.Constants.CONTENT_TYPE;
import static com.auto.framework.constants.Constants.TESTDATA_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.auto.framework.listeners.TestListener;
import com.auto.framework.testdata.UserModal;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Date : Nov. 28, 2023
 * @Author : naveenchr
 * @Description : Standalone API test sample
 * @Version : 1.0
 ************************************************************************************************************************/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Listeners(TestListener.class)
@Slf4j
public class APITests extends AbstractTestNGSpringContextTests {
	@Test
	public void apiDataTest__TC004() {
		Response response = given().baseUri(TESTDATA_URI).auth().none().log().all()
				.header(CONTENT_TYPE, APPLICATION_JSON).when().get().then().extract().response();
		log.info("Response Details : {}", response.getBody().as(UserModal.class));

		assertThat(String.valueOf(response.getStatusCode()), containsString("200"));

	}
}
