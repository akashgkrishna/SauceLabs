
# BookCart Test Automation Framework

[![Java](https://img.shields.io/badge/Java-22-blue)](https://www.java.com/)
[![Selenium](https://img.shields.io/badge/Selenium-4.27.0-green)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red)](https://testng.org/)

A Selenium-based test automation framework for the BookCart web application, supporting multiple environments.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Best Practices](#best-practices)
- [Next Steps](#next-steps)
- [License](#license)

## Features
- Environment-specific configuration (QA/Prod/Staging)
- WebDriver management using WebDriverManager
- Page Object Model (POM) ready structure
- TestNG integration for test execution
- Configurable through properties files

## Prerequisites
- Java JDK 22
- Maven 3.6+
- Chrome Browser (latest version)
- IDE (IntelliJ/Eclipse)

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/bookcart-automation.git
2.  Build the project:
    
 
    `mvn clean install` 
    

## Configuration

1.  Environment setup:
    
    -   Edit `src/main/resources/config.properties` to modify environment URLs
    -   Supported environments: QA, Prod, Staging
    -   Default environment: QA
2.  Browser configuration:
    
    -   Managed automatically by WebDriverManager
    -   Chrome is the default browser

## Running Tests

### Command Line

Run all tests with default (QA) environment:

`mvn test` 

Run with specific environment:

`mvn test -Denv=prod` 

### Supported Environments
`-Denv=qa      # QA environment (default)`
`-Denv=prod    # Production environment`
`-Denv=staging # Staging environment` 
    

## Project Structure

![enter image description here](https://github.com/user-attachments/assets/9ad19303-4a46-462f-9089-0616a8c219d7)


## Best Practices

-   **Page Object Model**: Ready for POM implementation
-   **Environment Handling**: Easy switch between different environments
-   **Configuration**: Externalized properties for easy maintenance

## Next Steps

-   Add more test cases for application features
-   Implement Page Object Model pattern
-   Add logging with Log4j2
-   Integrate with CI/CD pipelines
-   Generate reports

