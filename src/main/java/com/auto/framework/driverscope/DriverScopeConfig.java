package com.auto.framework.driverscope;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/************************************************************************************************************************
 * @Date : Sep. 25, 2023
 * @Author : naveenchr
 * @Description : Creating a BeanFactoryPostProcessor for new DriverScope 
 * @Version : 1.0
 ************************************************************************************************************************/
@Configuration
public class DriverScopeConfig {

	@Bean
	public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
		return new DriverScopePostProcessor();
	}

}
