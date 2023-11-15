package com.auto.framework.driverscope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/************************************************************************************************************************
 * @Date : Sep. 25, 2023
 * @Author : naveenchr
 * @Description : Registering driverscope to BeanFactoryPostProcessor 
 * @Version : 1.0
 ************************************************************************************************************************/
public class DriverScopePostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		beanFactory.registerScope("driverscope", new DriverScope());
	}

}
