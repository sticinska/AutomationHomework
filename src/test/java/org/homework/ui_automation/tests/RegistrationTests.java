package org.homework.ui_automation.tests;

import org.homework.common.util.TestDataLoader;
import org.homework.ui_automation.constants.ErrorMessages;
import org.homework.ui_automation.model.User;

import org.homework.ui_automation.model.UserData;
import org.testng.annotations.*;


public class RegistrationTests extends BaseTest {
    private static UserData users;

    @BeforeClass(alwaysRun = true)
    public static void loadUsers() {
        users = TestDataLoader.loadUserFromFile();
    }

    @Test(groups = {"signupAndLogin", "smoke"},
            testName = "TC1 Register and Delete new User")
    public void registerAndDeleteNewUser() {
        User user = users.getValidUser();
        loginPage.assertHomePageVisible();
        loginPage.navigateToLogin();
        var registrationPage = loginPage.signupNewUser(user.getEmail(), user.getFirstname());
        registrationPage.assertRegistrationFormVisible();
        registrationPage.fillAllUserInfo(user);
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

    @Test(groups = "signupAndLogin",
            testName = "Register User before TC2")
    public void registerNewUser() {
        User user = users.getValidUser();
        loginPage.assertHomePageVisible();
        loginPage.navigateToLogin();
        var registrationPage = loginPage.signupNewUser(user.getEmail(), user.getFirstname());
        registrationPage.assertRegistrationFormVisible();
        registrationPage.fillAllUserInfo(user);
        registrationPage.submitRegistrationForm();
        registrationPage.assertRegistrationSuccess();
        registrationPage.continueProcess();
        registrationPage.assertUserLoggedIn();
        registrationPage.navigateToLogout();
        loginPage.assertLoginPageVisible();
    }


    @Test(groups = "signupAndLogin",
            dependsOnMethods = "registerNewUser",
            testName = "TC2 Login User with correct email and password")
    public void loginAndDeleteUser() {
        User user = users.getValidUser();
        loginPage.assertHomePageVisible();
        loginPage.navigateToLogin();
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.deleteAccount();
        loginPage.assertDeletionSuccess();
        loginPage.continueProcess();
    }

    @Test(groups = {"signupAndLogin"},
            testName = "TC3 Login User with incorrect email and password")
    public void loginWithIncorrectEmailAndPassword() {
        User user = users.getInvalidUser();
        loginPage.assertHomePageVisible();
        loginPage.navigateToLogin();
        loginPage.assertLoginPageVisible();
        loginPage.login(user.getEmail(), user.getPassword().toUpperCase());
        loginPage.assertErrorMessageVisible(ErrorMessages.INVALID_LOGIN);
    }

    @Test(groups = {"signupAndLogin", "smoke"},
            testName = "TC4 Logout User")
    public void loginAndLogout() {
        User user = users.getRegisteredUser();
        loginPage.assertHomePageVisible();
        loginPage.navigateToLogin();
        loginPage.login(user.getEmail(), user.getPassword());
        loginPage.assertUserLoggedIn();
        loginPage.navigateToLogout();
    }

    @Test(groups = {"signupAndLogin"},
            testName = "TC5 Register User with existing email")
    public void registerWithExistingEmail() {
        User user = users.getRegisteredUser();
        loginPage.assertHomePageVisible();
        loginPage.navigateToLogin();
        loginPage.signupNewUser(user.getEmail(), user.getFirstname());
        loginPage.assertErrorMessageVisible(ErrorMessages.EMAIL_ALREADY_EXISTS);
    }

}

