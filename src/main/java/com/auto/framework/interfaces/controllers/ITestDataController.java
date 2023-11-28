package com.auto.framework.interfaces.controllers;

import org.springframework.http.ResponseEntity;

import com.auto.framework.testdata.UserModal;

/************************************************************************************************************************
 * @Date : Nov. 28, 2023
 * @Author : naveenchr
 * @Description : Interface for TestDataController
 * @Version : 1.0
 ************************************************************************************************************************/
public interface ITestDataController {
	ResponseEntity<UserModal> getUserTestData();

}
