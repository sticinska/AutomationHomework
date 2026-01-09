package org.homework.ui_automation.tests;

import org.homework.common.util.TestDataLoader;
import org.homework.ui_automation.model.User;

import org.testng.Assert;
import org.testng.annotations.Test;



public class RegistrationTests extends BaseTest {

    private final TestDataLoader dataLoader = new TestDataLoader();

    @Test
    public void registerNewUser() {
        User newUser = dataLoader.loadUserFromFile(); //TODO: should load a specific user "valid user"
        var registrationPage = loginPage.signupNewUser(newUser.getEmail(),newUser.getFirstname());
        //TODO: verify registration page opens
        registrationPage.fillMandatoryUserInfo(newUser);
        /*
            TODO: add filling following fields
                - date of birht
                - select both checkboxes
                - company
                - address 2
         */
        registrationPage.submitRegistrationForm();
        Assert.assertTrue(registrationPage.successMessageVisible());
        /*
         TODO - Click 'Continue' button
              - Verify that 'Logged in as username' is visible
              - Click 'Delete Account' button
              - Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
         */
    }

}

