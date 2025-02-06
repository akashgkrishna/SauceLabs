# BookCart Test Automation Framework

[![Java](https://img.shields.io/badge/Java-22-blue)](https://www.java.com/)  [![Selenium](https://img.shields.io/badge/Selenium-4.27.0-green)](https://www.selenium.dev/)  [![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red)](https://testng.org/)  [![Logback](https://img.shields.io/badge/Logback-1.5.16-lightgrey)](https://logback.qos.ch/)

A Selenium-based test automation framework for the BookCart web application, supporting multiple environments and custom logging.

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
- Environment-specific configuration (QA/Prod/Staging)
- WebDriver management using WebDriverManager
- Page Object Model (POM) ready structure
- TestNG integration for test execution
- Configurable through properties files
- Custom Logging with LogManager & CustomLogger implementations
- SLF4J/Logback Integration with Console & File Logging

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
- Managed automatically by WebDriverManager
- Chrome is the default browser

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
bookcart-automation/
├── src/
│   ├── main/
│   │   ├── java/org/bookcart/
│   │   │   ├── base/              # Test base classes
│   │   │   └── util/              # Framework utilities
│   │   │       ├── logging/       # Custom logging implementation
│   │   │       │   ├── LogManager.java
│   │   │       │   └── CustomLogger.java
│   │   │       ├── ConfigManager.java
│   │   │       └── CredentialsManager.java
│   │   └── resources/             # Configuration files
│   └── test/
│       ├── java/                  # Test implementations
│       └── resources/             # TestNG configuration
├── pom.xml
└── README.md

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

### Driver Management

```java
@AfterMethod
public void tearDown() {
    if(driver != null) driver.quit();
}

```

## Future Roadmap

-   Implement Test Reports
-   Add BrowserStack Integration
-   Create CI/CD Pipeline

## Report Issues

-   [GitHub Issues](https://github.com/akashgkrishna/BookCart/issues)

## License

This project is licensed under the [MIT License](https://github.com/akashgkrishna/BookCart/blob/main/LICENSE)