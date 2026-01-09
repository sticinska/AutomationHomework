package org.homework.ui_automation.pages;

import org.homework.ui_automation.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterUserPage extends BasePage {

    private final By titleMr = By.id("id_gender1");
    private final By titleMrs = By.id("id_gender2");
    private final By password = By.id("password");
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By addressName = By.id("address1");
    private final By countryDropdown = By.id("country");
    private final By stateField = By.id("state");
    private final By cityField = By.id("city");
    private final By zipcodeField = By.id("zipcode");
    private final By phoneNumberField = By.id("mobile_number");

    private final By submitButton = By.xpath("//button[@data-qa='create-account']");
    private final By successMessage = By.xpath("//h2[@data-qa='account-created']");

    public void setTitle(String gender) {
        if (gender.equals("M")) {
            click(titleMr);
        } else {
            click(titleMrs);
        }
    }

    public void setPassword(String pass) {
        set(password, pass);
    }

    public void setFirstName(String name) {
        set(firstName, name);
    }

    public void setLastName(String name) {
        set(lastName, name);
    }

    public void setAddress(String street) {
        set(addressName, street);
    }

    public void selectCountry(String country) {
        selectByText(countryDropdown, country);
    }

    public void setState(String state) {
        set(stateField, state);
    }

    public void setCity(String city) {
        set(cityField, city);
    }

    public void setZipcode(String zipcode) {
        set(zipcodeField, zipcode);
    }

    public void setPhoneNumber(String phoneNumber) {
        set(phoneNumberField, phoneNumber);
    }

    public void fillMandatoryUserInfo(User user){
        setTitle(user.getGender());
        setFirstName(user.getFirstname());
        setLastName(user.getLastname());
        setPassword(user.getPassword());
        selectCountry(user.getCountry());
        setAddress(user.getAddress());
        setState(user.getState());
        setCity(user.getCity());
        setZipcode(user.getZipcode());
        setPhoneNumber(user.getPhone());
    }

    public void submitRegistrationForm(){
        click(submitButton);
    }

    public boolean successMessageVisible() {
        return !findMultiple(successMessage).isEmpty();
    }



}

