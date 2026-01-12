package org.homework.ui_automation.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By loginEmail = By.xpath("//input[@data-qa='login-email']");
    private final By loginPassword = By.xpath("//input[@data-qa='login-password']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");


    private final By signupName = By.xpath("//input[@data-qa='signup-name']");
    private final By signupEmail = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");


    private final By homepageTitle = By.xpath("//h1[contains(.,'Automation')]");
    private final By loginTitle = By.xpath("//h2[contains(.,'Login to your account')]");


    public void setLoginEmail(String username) {
        set(loginEmail, username);
    }

    public void setLoginPassword(String password) {
        set(loginPassword, password);
    }

    public void setSignupEmail(String username) {
        set(signupEmail, username);
    }

    public void setSignupName(String name) {
        set(signupName, name);
    }

    public void clickLoginButton() {
        click(loginButton);
    }


    public RegisterUserPage clickSignupButton() {
        click(signupButton);
        return new RegisterUserPage();
    }

    public RegisterUserPage signupNewUser(String email, String name) {
        setSignupEmail(email);
        setSignupName(name);
        return clickSignupButton();
    }

    public void login(String email, String password) {
        setLoginEmail(email);
        setLoginPassword(password);
        clickLoginButton();
    }

    public void assertHomePageVisible() {
        assertVisible(homepageTitle);
    }

    public void assertLoginPageVisible() {
        assertVisible(loginTitle);
    }

    public void assertErrorMessageVisible(String error) {
        assertVisible(By.xpath("//p[contains(.,'" + error + "')]"));
    }
}
