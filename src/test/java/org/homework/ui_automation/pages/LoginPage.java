package org.homework.ui_automation.pages;

import io.qameta.allure.Step;
import org.homework.common.util.EmailGenerator;
import org.homework.ui_automation.model.User;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By loginEmail = By.xpath("//input[@data-qa='login-email']");
    private final By loginPassword = By.xpath("//input[@data-qa='login-password']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");


    private final By signupName = By.xpath("//input[@data-qa='signup-name']");
    private final By signupEmail = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");


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

    @Step
    public void clickLoginButton() {
        click(loginButton);
    }

    @Step
    public RegisterUserPage clickSignupButton() {
        click(signupButton);
        return new RegisterUserPage();
    }

    @Step
    public RegisterUserPage signupUniqueUser(User user) {
        user.setEmail(EmailGenerator.generateUniqueEmail());
        setSignupEmail(user.getEmail());
        setSignupName(user.getFirstname());
        return clickSignupButton();
    }

    @Step
    public RegisterUserPage signupUser(User user) {
        setSignupEmail(user.getEmail());
        setSignupName(user.getFirstname());
        return clickSignupButton();
    }

    @Step
    public void login(String email, String password) {
        setLoginEmail(email);
        setLoginPassword(password);
        clickLoginButton();
    }


    @Step
    public boolean isLoginPageVisible() {
        return assertVisible(loginTitle);
    }


    @Step
    public boolean isErrorMessageVisible(String error) {
        return assertVisible(By.xpath("//p[contains(.,'" + error + "')]"));
    }
}
