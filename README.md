# Download Electoral Roll 

This project is a web automation task designed to download electoral rolls based on specific inputs such as district, assembly constituency, language preference, and polling station. The project uses Selenium for web automation and Tesseract for optical character recognition (OCR) to handle CAPTCHA images.

## Table of Contents

- [Project Description](#project-description)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Project Description

This project automates the process of downloading electoral rolls. It selects a district and assembly constituency, chooses the language for the electoral roll, and handles CAPTCHA verification using OCR.

## Technologies Used

- **Java**: Programming language used for the project.
- **Selenium**: Web automation framework.
- **Tesseract OCR**: Library for optical character recognition.
- **TestNG**: Testing framework.
- **Apache Maven**: Dependency management and build tool.

## Installation

### Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Tesseract installed on your system

### Steps

1. **Clone the repository:**

    ```bash
    git clone https://github.com/ansarifawwad/InfiniteAnalytics_Assesment.git
    cd InfiniteAnalytics_Assesment
    ```

2. **Install dependencies:**

    ```bash
    mvn clean install
    ```

3. **Install Tesseract:**

    - On macOS using Homebrew:

      ```bash
      brew install tesseract
      ```

    - On Windows or Linux, follow the instructions [here](https://github.com/tesseract-ocr/tesseract).

4. **Configure Tesseract Path:**

    Ensure that the path to the Tesseract executable is correctly set in your environment or in the code where `tesseract.setDatapath` is called.

## Usage

1. **Run the Tests:**

    - Ensure that all required data providers and configurations are correctly set.
    - Execute the tests using TestNG in IntelliJ IDEA or through Maven:

      ```bash
      mvn test
      ```

## Running Tests

The project uses TestNG for running tests. Ensure all tests are correctly configured in the `testng.xml` file. You can run tests from IntelliJ IDEA or using Maven:

```bash
mvn test
```
