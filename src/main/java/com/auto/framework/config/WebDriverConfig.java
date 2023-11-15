package com.auto.framework.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Date : Sep. 13, 2023
 * @Author : naveenchr
 * @Description : Bean configuration for WebDriver and WebDriver wait object
 *              based on browser
 * @Version : 1.0
 ************************************************************************************************************************/

@Slf4j
@Configuration
public class WebDriverConfig {

	@Autowired
	private MyProperties myProperties;

	@Bean
	@ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('chrome')")
	@Scope("driverscope")
	public WebDriver getChromeDriver() {
		log.info("Creating Driver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-debugging-port=9222");
		options.addArguments("--remote-allow-origins=*");
//		options.addArguments("--headless=new");
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(options);
	}

	@Bean
	@ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('chrome')")
	@Scope("driverscope")
	public WebDriverWait getChromeDriverWait() {
		return new WebDriverWait(getChromeDriver(), myProperties.getExplicitTimeout());
	}

	@Bean
	@ConditionalOnProperty(name = "my.properties.grid", havingValue = "true")
	@Scope("driverscope")
	public WebDriver getGridDriver() throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(myProperties.getBrowser());
		dc.setCapability("e34:token", myProperties.getGridToken());
		log.info("Creating Driver");
		WebDriver driver = new RemoteWebDriver(new URL(myProperties.getGridUrl()), dc);
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		return driver;
	}

	@Bean
	@ConditionalOnProperty(name = "my.properties.grid", havingValue = "true")
	@Scope("driverscope")
	public WebDriverWait getGridDriverWait() throws MalformedURLException {
		return new WebDriverWait(getGridDriver(), myProperties.getExplicitTimeout());
	}

	@Bean
	@ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('edge')")
	@Scope("driverscope")
	public WebDriver getEdgeDriver() {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--remote-allow-origins=*");
//		options.addArguments("--headless=new");
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver(options);
	}

	@Bean
	@ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('edge')")
	@Scope("driverscope")
	public WebDriverWait getEdgeDriverWait() {
		return new WebDriverWait(getEdgeDriver(), myProperties.getExplicitTimeout());
	}

}
