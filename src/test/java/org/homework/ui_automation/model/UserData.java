package org.homework.ui_automation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {


    @JsonProperty("valid_user")
    private User validUser;

    @JsonProperty("invalid_user")
    private User invalidUser;

    @JsonProperty("registered_user")
    private User registeredUser;

    public User getValidUser() {
        return validUser;
    }

    public User getInvalidUser() {
        return invalidUser;
    }

    public User getRegisteredUser() {
        return registeredUser;
    }
}
