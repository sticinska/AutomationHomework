package org.homework.api_automation.apis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.homework.common.util.HttpStatus;

import java.io.File;

public class ProductsApi {


    private static final String PRODUCTS_LIST_ENDPOINT = "api/productsList";


    public static Response getProductList() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(PRODUCTS_LIST_ENDPOINT);
    }

    public static Response postToProductsList(File requestBody) {
        return RestAssured
                .given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when()
                .post(PRODUCTS_LIST_ENDPOINT);
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
