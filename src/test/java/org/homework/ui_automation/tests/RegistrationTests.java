package org.homework.ui_automation.tests;

import org.homework.common.util.TestDataLoader;
import org.homework.ui_automation.constants.ErrorMessages;
import org.homework.ui_automation.model.User;

import org.homework.ui_automation.model.UserData;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;


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

        assertTrue(loginPage.isHomePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        var registrationPage = loginPage.signupUniqueUser(user);

        assertTrue(registrationPage.isRegistrationFormVisible(),"Registration form should be visible");
        registrationPage.fillAllUserInfo(user);
        registrationPage.checkNewsletterCheckbox();
        registrationPage.checkSpecialOffersCheckbox();
        registrationPage.submitRegistrationForm();
        assertTrue(registrationPage.isRegistrationSuccessful(), "'Account Created' message visible");

        registrationPage.clickContinueAfterRegistration();
        assertTrue(registrationPage.isUserLoggedIn(), "User should be logged in");

        registrationPage.deleteAccount();
        assertTrue(registrationPage.isDeletionSuccessful(), "'Account Deleted' message visible");
    }


    @Test(groups = "signupAndLogin",
            testName = "TC2 Login User with correct email and password")
    public void loginAndDeleteUser() {
        User user = createAndRegisterUser();
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.deleteAccount();
        assertTrue(loginPage.isDeletionSuccessful(), "'Account Deleted' message visible");

        loginPage.clickContinueAfterDeletion();
    }

    @Test(groups = {"signupAndLogin"},
            testName = "TC3 Login User with incorrect email and password")
    public void loginWithIncorrectEmailAndPassword() {
        User user = users.getInvalidUser();
        assertTrue(loginPage.isHomePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        assertTrue(loginPage.isLoginPageVisible(), "Login page should be visible");
        loginPage.login(user.getEmail(), user.getPassword().toUpperCase());
        assertTrue(loginPage.isErrorMessageVisible(ErrorMessages.INVALID_LOGIN),
                "Error message \""+ ErrorMessages.INVALID_LOGIN + "\" should be visible");
    }

    @Test(groups = {"signupAndLogin", "smoke"},
            testName = "TC4 Logout User")
    public void loginAndLogout() {
        User user = users.getRegisteredUser();
        assertTrue(loginPage.isHomePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.isUserLoggedIn(), "User should be logged in");
        loginPage.logout();
        assertTrue(loginPage.isLoginPageVisible(), "Login page should be visible");
    }

    @Test(groups = {"signupAndLogin"},
            testName = "TC5 Register User with existing email")
    public void registerWithExistingEmail() {
        User user = users.getRegisteredUser();
        assertTrue(loginPage.isHomePageVisible(), "Home page should be visible");
        loginPage.navigateToLogin();
        loginPage.signupUser(user);
        assertTrue(loginPage.isErrorMessageVisible(ErrorMessages.EMAIL_ALREADY_EXISTS),
                "Error message \""+ ErrorMessages.EMAIL_ALREADY_EXISTS + "\" should be visible");;
    }

    //TODO ADD CLEAN UP - DELETE EACH GENERATED EMAIL USER

}

