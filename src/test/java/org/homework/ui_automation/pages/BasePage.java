package org.homework.ui_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BasePage {

    public static WebDriver driver;
    private final By dataUsageConsentButton = By.xpath("//button[contains(@class,'fc-cta-consent')]");

    public void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findMultiple(By locator) {
        return driver.findElements(locator);
    }

    protected void set(By locator, String text) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    protected void click(By locator) {
        find(locator).click();
    }

    protected void selectByText(By locator, String text) {
        Select select = new Select(find(locator));
        select.selectByVisibleText(text);
    }

    protected void navigateToPage(String title) {
        find(By.xpath("//a[contains(.,'" + title + "')]")).click();
    }


    public void confirmDataConsent(){
        if (find(dataUsageConsentButton).isDisplayed()){
            click(dataUsageConsentButton);
        }
    }

}
