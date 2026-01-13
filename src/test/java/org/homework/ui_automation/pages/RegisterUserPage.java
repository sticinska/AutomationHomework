package org.homework.ui_automation.pages;

import io.qameta.allure.Step;
import org.homework.ui_automation.model.User;
import org.openqa.selenium.By;


public class RegisterUserPage extends BasePage {

    private final By titleMr = By.id("id_gender1");
    private final By titleMrs = By.id("id_gender2");
    private final By password = By.id("password");
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By dayDropdown = By.id("days");
    private final By monthDropdown = By.id("months");
    private final By yearDropdown = By.id("years");
    private final By addressName = By.id("address1");
    private final By addressName2 = By.id("address2");
    private final By countryDropdown = By.id("country");
    private final By companyField = By.id("company");
    private final By stateField = By.id("state");
    private final By cityField = By.id("city");
    private final By zipcodeField = By.id("zipcode");
    private final By phoneNumberField = By.id("mobile_number");

    private final By newsletterCheckbox = By.id("newsletter");
    private final By specialOffersCheckbox = By.id("optin");


    private final By submitButton = By.xpath("//button[@data-qa='create-account']");
    private final By registrationFormTitle = By.xpath("//h2[contains(.,'Enter Account Information')]");
    private final By registrationSuccessMessage = By.xpath("//h2[@data-qa='account-created']");



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

    //method assumes birthdate is in format YYYY.MM.DD
    public void setBirthdate(String birthdate) {
        String[] date = birthdate.split("\\.");

        String year = date[0];
        String month = date[1];
        String day = date[2];

        int dayInt = Integer.parseInt(day);
        int monthInt = Integer.parseInt(month);

        selectByValue(dayDropdown, String.valueOf(dayInt));
        selectByValue(monthDropdown, String.valueOf(monthInt));
        selectByValue(yearDropdown, year);
    }


    public void setAddress(String street) {
        set(addressName, street);
    }

    public void setAddress2(String street) {
        set(addressName2, street);
    }

    public void selectCountry(String country) {
        selectByText(countryDropdown, country);
    }

    public void setCompany(String company) {
        set(companyField, company);
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

    @Step
    public void fillAllUserInfo(User user) {
        if (user.getGender() != null) setTitle(user.getGender());
        if (user.getFirstname() != null) setFirstName(user.getFirstname());
        if (user.getLastname() != null) setLastName(user.getLastname());
        if (user.getPassword() != null) setPassword(user.getPassword());
        if (user.getCountry() != null) selectCountry(user.getCountry());
        if (user.getAddress() != null) setAddress(user.getAddress());
        if (user.getAddress2() != null) setAddress2(user.getAddress2());
        if (user.getState() != null) setState(user.getState());
        if (user.getCity() != null) setCity(user.getCity());
        if (user.getZipcode() != null) setZipcode(user.getZipcode());
        if (user.getPhone() != null) setPhoneNumber(user.getPhone());
        if (user.getCompany() != null) setCompany(user.getCompany());
        if (user.getBirthdate() != null) setBirthdate(user.getBirthdate());
    }

    @Step
    public void checkNewsletterCheckbox() {
        click(newsletterCheckbox);
    }

    @Step
    public void checkSpecialOffersCheckbox() {
        click(specialOffersCheckbox);
    }

    @Step
    public void submitRegistrationForm() {
        scrollAndClick(submitButton);
    }

    @Step
    public boolean isRegistrationSuccessful() {
        return assertVisible(registrationSuccessMessage);
    }

    @Step
    public void clickContinueAfterRegistration() {
        continueProcess();
    }

    @Step
    public boolean isRegistrationFormVisible() {
        return assertVisible(registrationFormTitle);
    }
}

