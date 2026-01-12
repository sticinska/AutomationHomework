package org.homework.ui_automation.tests;

import org.homework.ui_automation.pages.BasePage;
import org.homework.ui_automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;

public abstract class BaseTest {
    protected WebDriver driver;
    private final String url = "https://www.automationexercise.com/";
    protected BasePage basePage;
    protected LoginPage loginPage;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);

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
}
