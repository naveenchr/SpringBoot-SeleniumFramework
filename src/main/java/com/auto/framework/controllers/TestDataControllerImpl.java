package com.auto.framework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auto.framework.interfaces.controllers.ITestDataController;
import com.auto.framework.interfaces.services.IUserTestDataService;
import com.auto.framework.testdata.UserModal;

/************************************************************************************************************************
 * @Date : Nov. 28, 2023
 * @Author : naveenchr
 * @Description : Controller implementation for TestData Micro Service
 * @Version : 1.0
 ************************************************************************************************************************/
@RestController
public class TestDataControllerImpl implements ITestDataController {

	@Autowired
	IUserTestDataService iUserTestDataService;

	@Override
	@GetMapping("/testuserdata")
	public ResponseEntity<UserModal> getUserTestData() {
		return new ResponseEntity<>(iUserTestDataService.getUserData(), HttpStatus.OK);
	}

}
