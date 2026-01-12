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

        assertTrue(loginPage.homePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        var registrationPage = loginPage.signupUniqueUser(user);
        generatedEmail = user.getEmail();

        assertTrue(registrationPage.assertRegistrationFormVisible(),"Registration form should be visible");
        registrationPage.fillAllUserInfo(user);
        registrationPage.checkNewsletterCheckbox();
        registrationPage.checkSpecialOffersCheckbox();
        registrationPage.submitRegistrationForm();
        assertTrue(registrationPage.registrationSuccessful(), "'Account Created' message visible");

        registrationPage.clickContinueAfterRegistration();
        assertTrue(registrationPage.userLoggedIn(), "User should be logged in");

        registrationPage.deleteAccount();
        assertTrue(registrationPage.deletionSuccessful(), "'Account Deleted' message visible");
    }


    @Test(groups = "signupAndLogin",
            testName = "TC2 Login User with correct email and password")
    public void loginAndDeleteUser() {
        User user = createAndRegisterUser();
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.deleteAccount();
        assertTrue(loginPage.deletionSuccessful(), "'Account Deleted' message visible");

        loginPage.clickContinueAfterDeletion();
    }

    @Test(groups = {"signupAndLogin"},
            testName = "TC3 Login User with incorrect email and password")
    public void loginWithIncorrectEmailAndPassword() {
        User user = users.getInvalidUser();
        assertTrue(loginPage.homePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        assertTrue(loginPage.loginPageVisible(), "Login page should be visible");
        loginPage.login(user.getEmail(), user.getPassword().toUpperCase());
        assertTrue(loginPage.assertErrorMessageVisible(ErrorMessages.INVALID_LOGIN),
                "Error message \""+ ErrorMessages.INVALID_LOGIN + "\" should be visible");
    }

    @Test(groups = {"signupAndLogin", "smoke"},
            testName = "TC4 Logout User")
    public void loginAndLogout() {
        User user = users.getRegisteredUser();
        assertTrue(loginPage.homePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.userLoggedIn(), "User should be logged in");
        loginPage.logout();
        assertTrue(loginPage.loginPageVisible(), "Login page should be visible");
    }

    @Test(groups = {"signupAndLogin"},
            testName = "TC5 Register User with existing email")
    public void registerWithExistingEmail() {
        User user = users.getRegisteredUser();
        assertTrue(loginPage.homePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        loginPage.signupUser(user);
        assertTrue(loginPage.assertErrorMessageVisible(ErrorMessages.EMAIL_ALREADY_EXISTS),
                "Error message \""+ ErrorMessages.EMAIL_ALREADY_EXISTS + "\" should be visible");;
    }

    //TODO ADD CLEAN UP - DELETE EACH GENERATED EMAIL USER

}

