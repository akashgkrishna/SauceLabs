
# BookCart Test Automation Framework

[![Java](https://img.shields.io/badge/Java-22-blue)](https://www.java.com/)  [![Selenium](https://img.shields.io/badge/Selenium-4.27.0-green)](https://www.selenium.dev/)  [![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red)](https://testng.org/)  [![Logback](https://img.shields.io/badge/Logback-1.5.16-lightgrey)](https://logback.qos.ch/)  [![Allure](https://img.shields.io/badge/Allure-2.29.1-blueviolet)](https://docs.qameta.io/allure/) [![Lombok](https://img.shields.io/badge/Lombok-1.18.36-pink)](https://projectlombok.org/)

A Selenium-based, cross-browser test automation framework for the BookCart web application, supporting multiple environments, data-driven testing, reporting with Allure and custom logging.

## Table of Contents

-   [Features](#features)
-   [Prerequisites](#prerequisites)
-   [Installation](#installation)
-   [Configuration](#configuration)
-   [Execution](#execution)
-   [Logging](#logging)
-   [Project Structure](#project-structure)
-   [Best Practices](#best-practices)
-   [Future Roadmap](#future-roadmap)
-   [License](#license)


## Features
- **Environment-Specific Configuration:** Easily switch between QA, Staging, and Production environments via the `config.properties` file and system properties.
- **Driver Management:** Uses centralised driver management.
- **Page Object Model (POM):** Well-organized POM structure with separate packages for base pages, common components, and specialized pages.
- **Test Flow Abstraction:** Business logic is encapsulated in flow classes (e.g., `LoginFlow` and `UserRegistrationFlow`) to simplify test implementations.
- **Data-Driven Testing:** Centralised test data generation and TestNG data providers ensures unique test runs.
- **Enhanced Reporting with Allure:** - Step annotations and screenshots on test failure are automatically attached to Allure reports. - Clear test descriptions, severity levels, and traceability with TMS links.
- **Custom Logging:** Uses SLF4J/Logback with custom logger implementations to provide clear and secure logging output.
- **Synchronization & Waits:** Leverages explicit waits via `WebDriverWait` and a custom `WaitUtils` class to ensure reliable test execution.
- **Lombok Integration**  - Clean model classes with  `@Builder`  pattern.
- **AspectJ Weaving**  - Runtime instrumentation for enhanced reporting.
- **Smart Waits**: Combination of explicit waits and utility methods.
- **Headless Execution**: Configurable via  `config.properties`.

## Prerequisites

-   **Java 22 JDK**
-   **Maven 3.6+**
-   **Chrome Browser (latest)**
-   **IDE** (IntelliJ/Eclipse/VSCode)

## Installation

1. Clone the repository:  
   `git clone git@github.com:akashgkrishna/BookCart.git `
2. Build the project:  
   `mvn clean install`

## Configuration

### Environment Setup: (`config.properties`)
- Edit `src/main/resources/config.properties` to modify environment URLs
- Supported environments: QA, Prod, Staging
- Default environment: QA
```properties
# Environment URLs
qa.url=https://bookcart.azurewebsites.net/
staging.url=https://staging-bookcart.azurewebsites.net/
prod.url=https://prod-bookcart.azurewebsites.net/

# Credentials
qa.username=qa_user
qa.password=qa_password
staging.username=staging_user
staging.password=staging_password
prod.username=prod_user
prod.password=prod_password

```

### Log Configuration: (`logback.xml`)

```xml
<!-- Configure logging patterns and output locations -->
<root level="trace">
    <appender-ref ref="CONSOLE"/>
    <appender-ref ref="FILE"/>
</root>

```
### Browser setup:
- Currently, supports (Chrome, Firefox, Safari and Edge)
- Chrome is the default browser .
- If you want other drivers just add the appropriate WebDriver instance in DriverManager class


```properties
# Browser configuration  
browser=chrome  
headless=true
```
### Safari Browser setup:
Safari's settings need to be adjusted to allow automation.
Follow these steps:

1.  **Enable the Develop Menu:**  
    Open Safari and go to **Safari > Preferences > Advanced**. Then, check the box that says **"Show Develop menu in menu bar"**.

2.  **Allow Remote Automation:**  
    Once the Develop menu is visible, click on **Develop** in the menu bar and select **"Allow Remote Automation"**. This setting permits WebDriver to control Safari.

3.  **Restart Safari:**  
    After enabling the setting, close Safari and re-launch it to ensure the changes take effect.


These adjustments should allow SafariDriver to create a new session successfully.

### Lombok setup
Verify Lombok is enabled in your IDE. Without Lombok processing, none of the builder methods or getters (provided by `@Getter` and `@Builder`) are generated.

**1. Add Lombok Dependency:**  
Ensure that your `pom.xml` contains the Lombok dependency. For example:
```xml
<dependency> 
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId> 
	<version>1.18.24</version> 
	<scope>provided</scope> 
</dependency>
```
**2. Enable Annotation Processing:**

-   If you’re using an IDE like IntelliJ IDEA or Eclipse, make sure that annotation processing is enabled.
    -   For Maven, the compiler plugin should pick up Lombok annotations if the dependency is correctly added.

**3. Clean and Rebuild:**  
After adjusting your configuration, perform a clean build (e.g., `mvn clean compile`) to verify that Lombok generates the expected methods.


### Allure Configuration (`allure.properties`)
```properties
allure.results.directory=target/allure-results
```

### Error Messages (`error-messages.properties`)

```properties
invalid.username=User Name is not available
invalid.password=Password requirements not met
```

### TestNG Suite (`testng.xml`)
```xml
<listeners>
    <listener class-name="io.qameta.allure.testng.AllureTestNg"/>
</listeners>
```



## Execution

### Default (QA Environment):

```bash
mvn test
```

### Specific Environment:

```bash
mvn test -Denv=prod
```

### Supported Environments:

-   `-Denv=qa` (default)
-   `-Denv=staging`
-   `-Denv=prod`
### Generate Allure Report

```bash
mvn allure:serve
```

## Logging

### Usage in Tests:

```java
public class BasicTest extends BaseTest {
    @Test
    public void exampleTest() {
        logger.info("Using credentials: {}", username);
        logger.error("Validation failed!");
    }
}
```

### Log Outputs:

```
14:23:45 INFO  [TestNG] o.b.BasicTest - Using credentials: test01
14:23:46 ERROR [TestNG] o.b.BasicTest - Validation failed!
```

## Project Structure

```
├── LICENSE                           // License information (e.g., MIT License)
├── README.md                         // Project documentation and usage instructions
├── pom.xml                           // Maven configuration with dependencies and build plugins
└── src
    ├── main
    │   ├── java/org/bookcart/         // Main application code
    │   │   ├── base/                  // Base test classes (e.g., BaseTest.java for setup/teardown)
    │   │   ├── data/                  // Test data factories (e.g., UserDataFactory.java)
    │   │   ├── driver/                // WebDriver management (e.g., DriverManager.java)
    │   │   ├── flows/                 // Business logic flows (e.g., LoginFlow.java, UserRegistrationFlow.java)
    │   │   ├── model/                 // Data models (e.g., User.java)
    │   │   ├── pages/                 // Page Object Model classes (e.g., LoginPage.java, RegisterPage.java)
    │   │   └── util/                  // Utility classes for various common functionalities:
    │   │       ├── ConfigManager.java         // Loads and manages configuration properties
    │   │       ├── CredentialsManager.java    // Retrieves user credentials based on the environment
    │   │       ├── MessageUtils.java          // Handles common messages (e.g., error/info messages)
    │   │       ├── ScreenshotUtils.java       // Captures screenshots for reporting, especially on failures
    │   │       ├── WaitUtils.java             // Provides explicit wait methods for synchronizing tests
    │   │       └── logging/                   // Custom logging utilities:
    │   │           ├── CustomLogger.java      // Wrapper for logging functionality (using SLF4J/Logback)
    │   │           └── LogManager.java        // Factory for instantiating and managing loggers
    │   └── resources/                 // Application configuration files
    │       ├── config.properties      // Environment URLs, credentials, and other settings
    │       └── logback.xml            // Logging configuration (patterns, output locations)
    └── test
        ├── java/org/bookcart/         // Test code
        │   ├── login/                 // Login-related test classes:
        │   ├── providers/             // Data providers for tests:
        │   │   └── UserDataProviders.java   // Supplies dynamic test data for user-related tests
        │   └── userregistration/      // User registration test
        └── resources/                 // Test configuration files
            ├── allure.properties      // Configuration for Allure reporting
            ├── error-messages.properties  // Common error messages used in tests
            └── testng.xml           // TestNG suite configuration for test execution



```

## Best Practices

### Logger Usage

```java
// Good
logger.info("Loading page: {}", url);

// Avoid
System.out.println("Loading page: " + url);

```

### Environment Isolation

```bash
# Never commit production credentials
# Keep sensitive data in environment variables

```

### Allure Reporting
**Add the necessary Annotations in test**
Example:
```java
@Test(dataProviderClass = UserDataProviders.class,  
        dataProvider = "invalidRegistrationData")  
@Description("Verify that registration fails when user enters existing username")  
@TmsLink("CEL-TC-46")  
@Severity(SeverityLevel.CRITICAL)
```
**Add proper Step when needed**
Example:
```java
@Step("Entering Credentials")  
public void enterCredentials(String username, String password) {  
    sendKeys(username, usernameTextField);  
    sendKeys(password, passwordTextField);  
}
```

### Lombok Models

```java
@Getter
@Builder
public class User {
    private final String username;
    private final String password;
}
```
### Data-Driven Testing

```java
@DataProvider
public Object[][] invalidLogins() {
    return new Object[][]{
            {"invalidUser", "wrongPass"},
            {"lockedUser", "secret123"}
    };
}
```

## Future Roadmap

-   Add BrowserStack Integration
-   Create CI/CD Pipeline
-  Grouping Tests
-  Parallel Testing -TestNG parallel test runs
- Docker Support - Containerized test execution

## Report Issues

-   [GitHub Issues](https://github.com/akashgkrishna/BookCart/issues)

## License

This project is licensed under the [MIT License](https://github.com/akashgkrishna/BookCart/blob/main/LICENSE)