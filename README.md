# Prerequisites 📝
- Install **Java 17+**
- Install **Gradle**
- Install **Chrome browser**
- Install **Allure**
- Ensure ChromeDriver is compatible with your Chrome version
    (WebDriverManager can be added later if desired)

# Running Test Suites ▶

To Run **Signup and Login UI Tests** run command
`./gradlew signupAndLoginTests`

To Run **Smoke Tests** run command
`./gradlew smokeTests`

To Run **API Tests** run command
`./gradlew ApiTests`

# Default TestNG reports 📉
After execution, default reports are available at:
_`build/reports/tests/test/index.html`_
To open them simply open the `index.html` file in a browser




# Generating Allure reports 📈
To generate Allure report for the specific test group run command
`allure generate - build/reports/allure-signupAndLogin`

To open this report run command
`allure open .\build\reports\allure-signupAndLogin`
