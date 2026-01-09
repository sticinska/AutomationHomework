package org.homework.ui_automation.tests;

import org.homework.ui_automation.pages.BasePage;
import org.homework.ui_automation.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
    protected WebDriver driver;
    private final String url = "https://www.automationexercise.com/";
    protected BasePage basePage;
    protected LoginPage loginPage;


    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        basePage = new BasePage();
        basePage.setDriver(driver);
        loginPage = new LoginPage();
        basePage.confirmDataConsent();
    }


    @AfterClass
    public void teardown() {
        //driver.quit();
    }
}
