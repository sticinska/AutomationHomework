package org.homework.ui_automation.tests;

import org.homework.common.util.TestDataLoader;
import org.homework.ui_automation.model.User;

import org.testng.annotations.Test;


public class RegistrationTests extends BaseTest {

    private final TestDataLoader dataLoader = new TestDataLoader();

    @Test
    public void registerAndDeleteNewUser() {
        User newUser = dataLoader.loadUserFromFile(); //TODO: should load a specific user "valid user"
        loginPage.assertHomePageVisible();
        var registrationPage = loginPage.signupNewUser(newUser.getEmail(), newUser.getFirstname());
        registrationPage.assertRegistrationFormVisible();
        registrationPage.fillAllUserInfo(newUser);
        registrationPage.checkNewsletterCheckbox();
        registrationPage.checkSpecialOffersCheckbox();
        registrationPage.submitRegistrationForm();
        registrationPage.assertRegistrationSuccess();
        registrationPage.continueProcess();
        registrationPage.assertUserLoggedIn();
        registrationPage.deleteAccount();
        registrationPage.assertDeletionSuccess();
        registrationPage.continueProcess();
    }

    @Test
    public void registerLogoutLoginDeleteFlow() {
        User newUser = dataLoader.loadUserFromFile(); //TODO: should load a specific user "valid user"
        loginPage.navigateToLogin();
        var registrationPage = loginPage.signupNewUser(newUser.getEmail(), newUser.getFirstname());
        registrationPage.fillAllUserInfo(newUser);
        registrationPage.checkNewsletterCheckbox();
        registrationPage.checkSpecialOffersCheckbox();
        registrationPage.submitRegistrationForm();
        registrationPage.assertRegistrationSuccess();
        registrationPage.continueProcess();
        registrationPage.navigateToLogout();
        loginPage.assertLoginPageVisible();
        loginPage.login(newUser.getEmail(), newUser.getPassword());
        registrationPage.assertUserLoggedIn();
    }

    @Test
    public void loginAndDeleteUser() {
        User newUser = dataLoader.loadUserFromFile(); //TODO: should load a specific user "valid user"
        loginPage.navigateToLogin();
        loginPage.login(newUser.getEmail(), newUser.getPassword());
        loginPage.deleteAccount();
        loginPage.assertDeletionSuccess();
        loginPage.continueProcess();
    }


}

