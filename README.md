# SauceLabs Test Automation Framework

[![Java](https://img.shields.io/badge/Java-22-blue)](https://www.java.com/)  [![Selenium](https://img.shields.io/badge/Selenium-4.27.0-green)](https://www.selenium.dev/)  [![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red)](https://testng.org/)  [![Logback](https://img.shields.io/badge/Logback-1.5.16-lightgrey)](https://logback.qos.ch/)  [![Allure](https://img.shields.io/badge/Allure-2.29.1-blueviolet)](https://docs.qameta.io/allure/) [![Lombok](https://img.shields.io/badge/Lombok-1.18.36-pink)](https://projectlombok.org/)

A Selenium-based, cross-browser test automation framework for the SauceDemo web application, supporting multiple environments, data-driven testing, reporting with Allure and custom logging.

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
-   [Report Issues](#report-issues)
-   [License](#license)

## Features

- **Environment Config:** Easily switch between QA, Staging, and Production via `config.properties` and system properties.
- **Driver Management:** Centralized driver handling for Chrome, Firefox, Safari, and Edge.
- **Page Object Model (POM):** Structured POM with separate packages for base and specialized pages.
- **Test Flow Abstraction:** Business logic encapsulated in flow classes (e.g., `LoginFlow`, `ShoppingCartFlow`).
- **Data-Driven Testing:** Centralized data generation with TestNG data providers for unique test runs.
- **Allure Reporting:** Auto-attached step annotations, failure screenshots, test descriptions, and severity levels.
- **Custom Logging:** SLF4J/Logback with custom logger for clear, secure logs.
- **Lombok Integration:** Clean models using `@Builder` and `@Getter`.
- **AspectJ Weaving:** Runtime instrumentation for enhanced reporting.
- **Smart Waits:** Mix of explicit waits and utility methods.
- **Headless Execution:** Configurable via `config.properties`.

## Prerequisites

- **Java 22 JDK**
- **Maven 3.6+**
- **Latest Chrome/Firefox Browser**
- **IDE:** IntelliJ IDEA / Eclipse / VSCode

## Installation

Clone the repository:
```bash
git clone git@github.com:akashgkrishna/SauceLabs.git
```
Build the project:

```bash
mvn clean install
```

## Configuration
### Environment Setup – `config.properties`
**Add/ Edit** `src/main/resources/config.properties` to modify environment URLs.

```properties
# Environment URLs
qa.url=https://www.saucedemo.com/
staging.url=https://staging.saucedemo.com/
prod.url=https://prod.saucedemo.com/

# Browser configuration "chrome", "firefox", "edge", "safari"
browser=firefox
headless=false

# Credentials
qa.username=standard_user
qa.password=secret_sauce

staging.username=testguy
staging.password=Testguy1

prod.username=test01
prod.password=Test@001
```

### Log Configuration – `logback.xml`
```xml
<root level="info">
    <appender-ref ref="CONSOLE"/>
    <appender-ref ref="FILE"/>
</root>
```
### Browser Setup
-   Currently, supports Chrome, Firefox, Safari and Edge
-   Firefox is the default browser
-   Configure via `browser` property in `config.properties`

```properties
# Browser configuration  
browser=chrome  
headless=true
```
### Safari Browser Setup
Safari's settings need to be adjusted to allow automation.  
Follow these steps:

1.  **Enable the Develop Menu:**  
    Open Safari and go to **Safari > Preferences > Advanced**. Then, check the box that says **"Show Develop menu in menu bar"**.

2.  **Allow Remote Automation:**  
    Once the Develop menu is visible, click on **Develop** in the menu bar and select **"Allow Remote Automation"**. This setting permits WebDriver to control Safari.

3.  **Restart Safari:**  
    After enabling the setting, close Safari and re-launch it to ensure the changes take effect.

### Lombok Setup
Verify Lombok is enabled in your IDE. Without Lombok processing, none of the builder methods or getters (provided by `@Getter` and `@Builder`) are generated.

**1. Add Lombok Dependency:**  
Ensure that your `pom.xml` contains the Lombok dependency. For example:
```xml
<dependency> 
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId> 
    <version>1.18.36</version> 
    <scope>provided</scope> 
</dependency>
```
**2. Enable Annotation Processing:**

-   If you're using an IDE like IntelliJ IDEA or Eclipse, make sure that annotation processing is enabled.


**3. Clean and Rebuild:**  
After adjusting your configuration, perform a clean build (e.g., `mvn clean compile`) to verify that Lombok generates the expected methods.

### Allure Configuration (`allure.properties`)
```properties
allure.results.directory=target/allure-results
```
### Error Messages (`error-messages.properties`)
```properties
#Login Page Errors
invalid.credentials=Epic sadface: Username and password do not match any user in this service
locked.out.user=Epic sadface: Sorry, this user has been locked out.
empty.field=Epic sadface: Username is required
empty.password=Epic sadface: Password is required
```

### TestNG Suite (`testng.xml`)
```xml
<listeners>
    <listener class-name="io.qameta.allure.testng.AllureTestNg"/>
</listeners>
```
## Execution
### Default (QA Environment - SauceDemo):
```bash
mvn test
```
### Specific Environment:
```bash
mvn test -Denv=prod
```
### Supported Environments:
-   `-Denv=qa` (default) - SauceDemo
-   `-Denv=staging`
-   `-Denv=prod`

### Generate Allure Report
```bash
mvn allure:serve
```

## Logging

### Usage in Tests:

```java
public class LoginTest extends BaseTest {
    @Test
    public void exampleTest() {
        logger.info("Using credentials: {}", username);
        logger.error("Validation failed!");
    }
}
```
### Log Outputs:
```text
14:23:45 INFO  [TestNG] o.s.LoginTest - Using credentials: standard_user
14:23:46 ERROR [TestNG] o.s.LoginTest - Validation failed!
```
## Project Structure
```text
├── LICENSE                           // License information (MIT License)
├── pom.xml                           // Maven configuration with dependencies and build plugins
├── README.md                         // Project documentation and usage instructions
└── src
    ├── main
    │   ├── java/org/saucelabs/       // Main application code
    │   │   ├── base/                 // Base test classes (BaseTest.java for setup/teardown)
    │   │   ├── driver/               // WebDriver management (DriverManager.java)
    │   │   ├── flows/                // Business logic flows (LoginFlow.java, ShoppingCartFlow.java)
    │   │   ├── model/                // Data models (User.java)
    │   │   ├── pages/                // Page Object Model classes
    │   │   │   ├── base/             // Base page class
    │   │   │   ├── common/           // Common components (Header, HomePage)
    │   │   │   ├── CartPage.java     // Shopping cart page
    │   │   │   ├── InventoryPage.java// Product inventory page
    │   │   │   └── LoginPage.java    // Login page
    │   │   └── utils/                // Utility classes
    │   │       ├── ConfigManager.java         // Loads and manages configuration properties
    │   │       ├── CredentialsManager.java    // Retrieves user credentials based on environment
    │   │       ├── MessageUtils.java          // Handles common messages
    │   │       ├── ScreenshotUtils.java       // Captures screenshots for reporting
    │   │       ├── WaitUtils.java             // Provides explicit wait methods
    │   │       └── logging/                   // Custom logging utilities
    │   └── resources/                // Application configuration files
    │       ├── config.properties      // Environment URLs, credentials, and browser settings
    │       └── logback.xml            // Logging configuration
    └── test
        ├── java/org/saucelabs/       // Test code
        │   ├── login/                // Login-related test classes
        │   ├── providers/            // Data providers for tests
        │   └── shoppingcart/         // Shopping cart tests
        └── resources/                // Test configuration files
            ├── allure.properties     // Configuration for Allure reporting
            ├── error-messages.properties  // Common error messages used in tests
            └── testng.xml            // TestNG suite configuration
            
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
@Test
@Description("Verify user can login with valid credentials")
@Severity(SeverityLevel.CRITICAL)
@Epic("Login")
@Feature("Login Functionality")
public void validLoginTest() {
    // Test implementation
}
```

**Add proper Step when needed**  
Example:
```java
@Step("Entering credentials")
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
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
}
```
### Data-Driven Testing

```java
@DataProvider(name = "invalidLoginCredentials")
public Object[][] invalidLoginCredentials() {
    return new Object[][]{
            {"standard_user", "invalidPassword"},
            {"invalidUsername", "secret_sauce"}
    };
}
```

## Test Coverage

The framework currently supports:

-   **Login Functionality**: Valid and invalid login scenarios, locked out user, performance glitch user

-   **Shopping Cart**: Add/remove products, cart badge updates, cart persistence

## Future Roadmap

-   Create CI/CD Pipeline

-   Grouping Tests

-   Parallel Testing - TestNG parallel test runs

-   Docker Support - Containerized test execution

-   API Testing Integration

-   Mobile Testing Support


## Report Issues

-   [GitHub Issues](https://github.com/akashgkrishna/SauceLabs/issues)


## License

This project is licensed under the [MIT License](https://github.com/akashgkrishna/SauceLabs/blob/main/LICENSE)