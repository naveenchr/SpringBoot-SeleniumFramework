package com.auto.framework.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/************************************************************************************************************************
 * @Date : Nov. 14, 2023
 * @Author : naveenchr
 * @Description : User properties class
 * @Version : 1.0
 ************************************************************************************************************************/
@Data
@ConfigurationProperties("my.properties")
@Component
public class MyProperties {
	private String browser;
	private Duration explicitTimeout;
	private String gridUrl;
	private String gridToken;
	private String username;
	private String password;
	private String grid;
	private String demoUrl;	
}
