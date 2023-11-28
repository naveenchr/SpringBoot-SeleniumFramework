package com.auto.framework.service;

import org.springframework.stereotype.Service;

import com.auto.framework.interfaces.services.IUserTestDataService;
import com.auto.framework.testdata.UserModal;
import com.github.javafaker.Faker;

/************************************************************************************************************************
 * @Date : Nov. 28, 2023
 * @Author : naveenchr
 * @Description : Service class for test data api
 * @Version : 1.0
 ************************************************************************************************************************/
@Service
public class UserTestDataServiceImpl implements IUserTestDataService {

	@Override
	public UserModal getUserData() {
		Faker faker = new Faker();
		return UserModal.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName())
				.age(faker.number().digits(2)).email(faker.internet().emailAddress())
				.currAddress(faker.address().fullAddress()).permAddress(faker.address().fullAddress())
				.salary(faker.number().digits(5)).department(faker.company().name()).build();
	}

}
