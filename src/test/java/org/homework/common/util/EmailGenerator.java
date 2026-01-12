package org.homework.common.util;

import java.util.ArrayList;
import java.util.List;

public class EmailGenerator {

    private static final List<String> generatedEmails = new ArrayList<>();

    public static String generateUniqueEmail(){

        String email = "qa" + System.currentTimeMillis() + "@homework.lv";
        generatedEmails.add(email);
        return email;
    }

    public static List<String> getGeneratedEmails() {
        return generatedEmails;
    }
}
