package com.auto.framework.testdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/************************************************************************************************************************
 * @Date : Oct. 17, 2023
 * @Author : naveenchr
 * @Description :Test Data modal class
 * @Version : 1.0
 ************************************************************************************************************************/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModal {
	private String firstName;
	private String lastName;
	private String email;
	private String currAddress;
	private String permAddress;
	private String age;
	private String salary;
	private String department;

}
