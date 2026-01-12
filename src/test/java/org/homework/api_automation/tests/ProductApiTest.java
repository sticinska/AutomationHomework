package org.homework.api_automation.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.homework.api_automation.apis.ProductsApi;
import org.homework.common.util.Environment;
import org.homework.common.util.HttpStatus;
import org.homework.common.util.TestDataLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductApiTest extends ProductsApi{


    @BeforeClass (alwaysRun = true)
    public static void setup() {
        RestAssured.baseURI = Environment.EXERCISE_URL;
    }

    @Test(groups = "ApiTests")
    public void getAllProductsList() {
        Response response = getProductList();
        assertEquals(response.statusCode(), HttpStatus.OK.getCode());
    }

    @Test(groups = "ApiTests")
    public void postToProductsList() {
        Response response = postToProductsList(TestDataLoader.loadProductFile());
        HttpStatus status = getStatusFromResponseBody(response);    //getting the response status from body
                                                                    // as the http request returns 200
        assertEquals(status.getCode(), HttpStatus.NOT_SUPPORTED.getCode());    }

}
