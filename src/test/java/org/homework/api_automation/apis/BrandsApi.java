package org.homework.api_automation.apis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class BrandsApi {
    private final String BRANDS_LIST_ENDPOINT = "/brandsList";


    public Response getBrandsList() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(BRANDS_LIST_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    public Response putToBrandsList(String responseBody) {
        return RestAssured
                .given()
                .body(responseBody)
                .contentType(ContentType.JSON)
                .put(BRANDS_LIST_ENDPOINT)
                .then()
                .extract()
                .response();
    }
}
