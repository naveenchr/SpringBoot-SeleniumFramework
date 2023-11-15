# Selenium Framework using Spring Boot

A test framework that utilizes Java Spring Boot capabilities to integrate key Selenium and TestNG features that can be used to create web-based automation scripts.

**Key Features**

* Supports Parallel test execution and thread management
* Random test data generation
* Spring dependency injection for object creation
* WebDriver manager-based browser initiation
* Platform independent
* Integration with Allure reports
* Integrated with sonarQube for vulnerability analysis
* Avoids boilerplate codes utilizing Lombok

# Table of contents
* [Installation](#installation)
* [Test Framework Design Details](#test-framework-design)
* [Test Execution Flow](#test-execution-flow)
* [Folder Structure](#folder-structure)
* [Running the tests](#running-the-tests)
* [Creating new tests](#creating-new-tests)
* [Generating sonar reports](#generating-sonar-and-jacoco-reports)
* [Built With](#built-with)
* [Contributing](#contributing)
* [License](#license)
* [References](#references)

# Test Framework Design Details

![Flowchart (7)](https://github.com/naveenchr/SpringBoot-SeleniumFramework/assets/11471191/88df5d16-199f-426b-b6c4-24bfebd602e8)


# Test Execution Flow

1.	Execution starts from **POM.xml**
2.	Control goes to the parameterized **Suites.xml**
3.	TestNG then invokes the methods in each of the Suites mentioned in Parallel mode based on XML config (For parallel runs separate threads are created)
4.	Control goes to the **ElementTests** test file
5.	Using Spring's dependency injection capabilities **BasePage** and **ElementsPage** objects are created
6.	Then using the **@BeforeMethod** annotation the Spring test context is prepared
7.	Execution then begins with the **SanityCheck()** method due to group dependency mentioned in the @Test annotations
8.	Control then transfers to the TestNG listener mentioned using the **@Listeners(TestListener.class)** annotation at the class level
9.	The overridden **onTestStart** is then invoked to log the start of the test execution
10.	The control then transfers back to the test to start with the **openElementsPage()** instruction
11.	The control is transferred then transferred to the ElementsPage class
12.	Then utilizing the **iUIElements** object of the **IUIElements** interface implemented by the **UIElements** Class the openURL method is invoked
13.	Only then do the IUIElements that inherit the **ActionsBaseClass** create the driver object since it is tagged with a **@Lazy** annotation
14.	This action is completed through the Spring's **@Configuration** annotated class **WebdriverConfig**
15.	Here checking on the **@ConditionalOnExpression** a Webdriver bean is created retrieving the parameters to be compared(browser=chrome, edge, etc.) from the **application.properties** file
16.	As soon the bean is created the lifecycle of the same is tagged to the TestNG thread that invoked the test case using the **@Scope("driverscope")** annotation
17.	The driverscope is already added to spring by extending and overriding the BeanFactoryPostProcessor interface
18.	This is done by creating a **BeanFactoryPostProcessor** bean using the **DriverScopeConfig** class and **DriverScope** class that inherits **SimpleThreadScope**
19.	The same @Scope("driverscope") annotation is then used to create a **WebDriverWait** bean to be utilized by various action classes
20.	With the invoking of the bean, a browser is spun up and the URL stored in a constant file is opened up
21.	Continuing on the test case steps, the page title is extracted utilizing a similar **iElementVerification** object of the **IElementVerification** interface implemented by the **ElementVerification** class
22.	The value retrieved is then asserted against the value in the test case to determine if the test case has to be passed or failed
23.	Based on the assertion, control is transferred to the **TestListener** to Pass/Fail the case
24.	The results are then added to the allure report by invoking the testReportUpdate method
25.	Using the **@AfterMethod(alwaysRun = true)** annotation the browsers are closed using the teardown() method in the BasePage
26.	Screenshots are also then saved and weaved into the **Allure report**
27.	Post sanity check completion, **textBoxVal_TC001()** is executed
28.	The test data to be used is injected using the **@dataprovider** annotation
29.	This is generated using **Java Faker API** in the UserDataProvider class
30.	The page actions are then performed using the Page Fragment class **TextBoxPF**
31.	Similarly the **checkBoxVal__TC002()** is also executed in parallel based on the TestNG config
32.	This is completed through the **CheckBoxPF** Page Fragment class
33.	System/Application properties can be manually updated in the **application.properties file** or overridden in the maven command

# Folder Structure
![Untitled (1)](https://github.com/naveenchr/SpringBoot-SeleniumFramework/assets/11471191/1eec2719-5e99-45d2-bf18-062e58cafbb0)


# Installation

Clone the repo from GitHub using below command
```git
git clone https://github.com/naveenchr/AutoFrameWork.git
```
Clean and compile the maven project using below commands

```maven
mvn clean
mvn compile
```

# Running the tests

**From Command Prompt**

```maven
mvn test
```
Parameters can be overridden as follows or updated directly on application.properties file

```maven
mvn test -Dmy.properties.grid-url=https://test.com/wd/hub -Dmy.properties.grid-token=123456 -Dmy.properties.grid=true
```

**Report Location**

1) Navigate to the "target" folder
2) Execute the below allure command

```maven
allure serve -h localhost
```

**Sample Report**
![Flowchart (5)](https://github.com/naveenchr/SpringBoot-SeleniumFramework/assets/11471191/35adcb9e-b19d-425e-95e4-ca5490e482dc)

![Flowchart (6)](https://github.com/naveenchr/SpringBoot-SeleniumFramework/assets/11471191/8017b4d8-15ee-47bd-b691-ee3639d70d68)

# Creating new tests

## Test Class File

```java
package com.auto.framework;

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
import com.auto.framework.testdata.UserDataProvider;
import com.auto.framework.testdata.UserModal;

@SpringBootTest
@Listeners(TestListener.class)
public class ElementTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private BasePage basePage;

	@Autowired
	public ElementsPage elementsPage;

	@Test(groups = "Sanity Test")
	public void sanityCheck() {
		elementsPage.openElementsPage();

		assertThat(elementsPage.getPageTitle(), is("DEMOQA"));
	}

	@Test(dependsOnGroups = "Sanity Test", dataProvider = "User Data", dataProviderClass = UserDataProvider.class)
	public void textBoxVal__TC001(UserModal userData) {
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
```
## Page Object File

```Java
package com.auto.framework.pageobjects.demoqa;

import static com.auto.framework.constants.Constants.ELEMENTS_PAGE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.auto.framework.pageobjects.common.BasePage;
import io.qameta.allure.Step;

@Component
public class ElementsPage extends BasePage {

	@Autowired
	public TextBoxPF textBoxPF;

	@Autowired
	public CheckBoxPF checkBoxPF;

	@Autowired
	public RadioButtonPF radioButtonPF;

	@Autowired
	public WebTablePF webTablePF;

	@Step("Open webpage")
	public void openElementsPage() {
		iUIElements.openURL(myProperties.getDemoUrl() + ELEMENTS_PAGE);
	}

	@Step("Verify Page Title")
	public String getPageTitle() {
		return iElementVerification.getTitle();
	}

}
```

# Generating sonar reports

* Add sonar server URL in POM file
```XML
<sonar.host.url>http://localhost:9000</sonar.host.url>
```
* Execute the below maven command after the test run
```maven
mvn sonar:sonar
```

# Built With

* Spring Boot
* Selenium WebDriver
* TestNG
* Maven
* Allure Reports
* Hamcrest
* Lombok
* Java Faker

# Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you want to change.

# License
[Apache 2.0](https://choosealicense.com/licenses/apache-2.0/)

# References
* https://www.youtube.com/watch?v=cG6ZLiRxn1M&list=PL6tu16kXT9PrDr6kMGQ-CgnvCsFxrq1eS
* https://www.youtube.com/watch?v=Ch163VfHtvA&list=PLsyeobzWxl7oA8QOlMtQsRT_I7Rx2hoX4
* https://www.youtube.com/@naveenautomationlabs
* https://www.udemy.com/course/selenium-real-time-examplesinterview-questions/
* https://www.udemy.com/course/spring-boot-testing/
* https://www.udemy.com/course/spring-5-with-spring-boot-2/
* https://allurereport.org/docs/gettingstarted-installation/
