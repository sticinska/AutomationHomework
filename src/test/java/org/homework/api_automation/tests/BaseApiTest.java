package org.homework.api_automation.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.homework.common.util.Environment;
import org.homework.common.util.HttpStatus;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {


    @BeforeClass(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = Environment.API_URL;
    }


    public HttpStatus getStatusFromResponseBody(Response response) {
        int responseCode = response.jsonPath().getInt("responseCode");

        for (HttpStatus status : HttpStatus.values()) {
            if (status.getCode() == responseCode) {
                return status;
            }
        }

        throw new IllegalArgumentException(
                "Unknown responseCode in body: " + responseCode
        );
    }

}
