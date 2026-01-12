package org.homework.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.homework.ui_automation.model.UserData;

import java.io.File;
import java.io.IOException;

public class TestDataLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String PATH = "src/test/resources/userData.json";

    public static UserData loadUserFromFile() {
        try {
            return objectMapper.readValue(new File(PATH), UserData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load user data from JSON", e);
        }
    }
}
