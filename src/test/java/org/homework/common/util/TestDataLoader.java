package org.homework.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.homework.ui_automation.model.UserData;

import java.io.File;
import java.io.IOException;

public class TestDataLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USER_DATA_PATH = "src/test/resources/userData.json";
    private static final String PRODUCT_DATA_PATH = "src/test/resources/userData.json";

    public static UserData loadUserFromFile() {
        try {
            return objectMapper.readValue(new File(PRODUCT_DATA_PATH), UserData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load user data from JSON", e);
        }
    }

    public static File loadProductFile() {
        return new File(USER_DATA_PATH);
    }
}
