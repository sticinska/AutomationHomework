package org.homework.ui_automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static WebDriver driver;
    private final By dataUsageConsentButton = By.xpath("//button[contains(@class,'fc-cta-consent')]");
    private final By continueButton = By.xpath("//a[@data-qa='continue-button']");
    private final By deletionSuccessMessage = By.xpath("//h2[@data-qa='account-deleted']");
    private final By loggedInTitle = By.xpath("//a[contains(.,'Logged in as')]");
    private final By homepageTitle = By.xpath("//h1[contains(.,'Automation')]");


    public void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    protected void set(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        waitForVisible(locator).click();
    }

    protected void scrollAndClick(By locator) {
        WebElement button = driver.findElement(locator);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", button
        );

        button.click();
    }


    protected void selectByText(By locator, String text) {
        Select select = new Select(waitForVisible(locator));
        select.selectByVisibleText(text);
    }

    protected void selectByValue(By locator, String value) {
        Select select = new Select(waitForVisible(locator));
        select.selectByValue(value);
    }

    protected void navigateToPage(String title) {
        click(By.xpath("//a[contains(.,'" + title + "')]"));
    }


    protected WebElement waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    @Step
    public void confirmDataConsent() {
        if (!driver.findElements(dataUsageConsentButton).isEmpty()) {
            driver.findElement(dataUsageConsentButton).click();
        }
    }

    protected boolean assertVisible(By locator) {
        try {
            waitForVisible(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void continueProcess() {
        click(continueButton);
    }

    @Step
    public void clickContinueAfterDeletion() {
        continueProcess();
    }

    public void deleteAccount() {
        navigateToPage("Delete Account");
    }

    @Step
    public void navigateToLogin() {
        navigateToPage("Login");
    }

    @Step
    public void logout() {
        navigateToPage("Logout");
    }

    @Step
    public boolean deletionSuccessful() {
        return assertVisible(deletionSuccessMessage);
    }

    @Step
    public boolean userLoggedIn() {
        return assertVisible(loggedInTitle);
    }

    @Step
    public boolean homePageVisible() {
        return assertVisible(homepageTitle);
    }

}

