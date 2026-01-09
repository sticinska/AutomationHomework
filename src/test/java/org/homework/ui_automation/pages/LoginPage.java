package org.homework.ui_automation.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By loginEmail = By.xpath("//input[@data-qa='login-email']");
    private final By loginPassword = By.xpath("//input[@data-qa='login-name']");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");


    private final By signupName = By.xpath("//input[@data-qa='signup-name']");
    private final By signupEmail = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");


    public void setloginEmail (String username) {
        set(loginEmail, username);
    }

    public void setLoginPassword (String password) {
        set(loginPassword, password);
    }

    public void setSignupEmail (String username) {
        set(signupEmail, username);
    }

    public void setSignupName (String password) {
        set(signupName, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }


    public RegisterUserPage clickSignupButton() {
        click(signupButton);
        return new RegisterUserPage();
    }

    public RegisterUserPage signupNewUser(String email, String name){
        navigateToPage("Signup");
        setSignupEmail(email);
        setSignupName(name);
        return clickSignupButton();
    }



}
