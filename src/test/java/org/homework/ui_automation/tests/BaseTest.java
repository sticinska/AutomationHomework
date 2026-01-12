package org.homework.ui_automation.tests;

import org.homework.common.util.Environment;
import org.homework.common.util.TestDataLoader;
import org.homework.ui_automation.model.User;
import org.homework.ui_automation.pages.BasePage;
import org.homework.ui_automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public abstract class BaseTest {
    protected WebDriver driver;
    protected BasePage basePage;
    protected LoginPage loginPage;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(Environment.EXERCISE_URL);

        basePage = new BasePage();
        basePage.setDriver(driver);
        loginPage = new LoginPage();
        loginPage.setDriver(driver);
        basePage.confirmDataConsent();
    }


    @AfterMethod(alwaysRun = true)
    public void teardown() {
       driver.quit();
    }

    protected User createAndRegisterUser() {
        User user = TestDataLoader.loadUserFromFile().getValidUser();

        loginPage.navigateToLogin();
        var registrationPage = loginPage.signupUniqueUser(user);

        registrationPage.fillAllUserInfo(user);
        registrationPage.submitRegistrationForm();
        registrationPage.clickContinueAfterRegistration();
        registrationPage.logout();

        return user;
    }


}
