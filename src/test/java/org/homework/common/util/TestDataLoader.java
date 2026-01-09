package org.homework.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.homework.ui_automation.model.User;

import java.io.File;
import java.io.IOException;

public class TestDataLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    //TODO: should be updated to be able to read a specific user from list of multiple json objects
    public User loadUserFromFile(){
        try {
            return objectMapper.readValue(new File("src/test/resources/userData.json"), User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
