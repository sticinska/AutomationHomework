package org.homework.ui_automation.tests;

import org.homework.common.util.TestDataLoader;
import org.homework.ui_automation.constants.ErrorMessages;
import org.homework.ui_automation.model.User;

import org.homework.ui_automation.model.UserData;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;


public class RegistrationTests extends BaseTest {
    private static UserData users;
    private static String generatedEmail;

    @BeforeClass(alwaysRun = true)
    public static void loadUsers() {
        users = TestDataLoader.loadUserFromFile();
    }

    @Test(groups = {"signupAndLogin", "smoke"},
            testName = "TC1 Register and Delete new User")
    public void registerAndDeleteNewUser() {
        User user = users.getValidUser();

        assertTrue(loginPage.homePageVisible());
        loginPage.navigateToLogin();
        var registrationPage = loginPage.signupUniqueUser(user);
        generatedEmail = user.getEmail();

        assertTrue(registrationPage.assertRegistrationFormVisible());
        registrationPage.fillAllUserInfo(user);
        registrationPage.checkNewsletterCheckbox();
        registrationPage.checkSpecialOffersCheckbox();
        registrationPage.submitRegistrationForm();
        assertTrue(registrationPage.registrationSuccessful());

        registrationPage.clickContinueAfterRegistration();
        assertTrue(registrationPage.userLoggedIn());

        registrationPage.deleteAccount();
        assertTrue(registrationPage.deletionSuccessful());
    }

    @Test(groups = "signupAndLogin",
            testName = "Register User before TC2")
    public void registerNewUser() {
        User user = users.getValidUser();

        assertTrue(loginPage.homePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        var registrationPage = loginPage.signupUniqueUser(user);
        generatedEmail = user.getEmail();

        assertTrue(registrationPage.assertRegistrationFormVisible());
        registrationPage.fillAllUserInfo(user);
        registrationPage.submitRegistrationForm();
        assertTrue(registrationPage.registrationSuccessful());

        registrationPage.clickContinueAfterRegistration();
        registrationPage.userLoggedIn();

        registrationPage.logout();
        assertTrue(loginPage.loginPageVisible());
    }


    @Test(groups = "signupAndLogin",
            dependsOnMethods = "registerNewUser",
            testName = "TC2 Login User with correct email and password")
    public void loginAndDeleteUser() {
        User user = users.getValidUser();

        assertTrue(loginPage.homePageVisible());
        loginPage.navigateToLogin();
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.deleteAccount();
        assertTrue(loginPage.deletionSuccessful());

        loginPage.clickContinueAfterDeletion();
        loginPage.logout();
    }

    @Test(groups = {"signupAndLogin"},
            testName = "TC3 Login User with incorrect email and password")
    public void loginWithIncorrectEmailAndPassword() {
        User user = users.getInvalidUser();
        assertTrue(loginPage.homePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        assertTrue(loginPage.loginPageVisible());
        loginPage.login(user.getEmail(), user.getPassword().toUpperCase());
        assertTrue(loginPage.assertErrorMessageVisible(ErrorMessages.INVALID_LOGIN));
    }

    @Test(groups = {"signupAndLogin", "smoke"},
            testName = "TC4 Logout User")
    public void loginAndLogout() {
        User user = users.getRegisteredUser();
        assertTrue(loginPage.homePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.userLoggedIn());
        loginPage.logout();
    }

    @Test(groups = {"signupAndLogin"},
            testName = "TC5 Register User with existing email")
    public void registerWithExistingEmail() {
        User user = users.getRegisteredUser();
        assertTrue(loginPage.homePageVisible());
        loginPage.navigateToLogin();
        loginPage.signupUser(user);
        assertTrue(loginPage.assertErrorMessageVisible(ErrorMessages.EMAIL_ALREADY_EXISTS));
    }

    //TODO ADD CLEAN UP - DELETE EACH GENERATED EMAIL USER

}

