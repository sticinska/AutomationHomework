package org.homework.api_automation.tests;

import io.restassured.response.Response;
import org.homework.api_automation.apis.BrandsApi;
import org.homework.common.util.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class BrandApiTest extends BaseApiTest {

    private final BrandsApi brandsApi = new BrandsApi();

    @Test(groups = "ApiTests",
            testName = "API 3: Get All Brands List")
    public void getAllBrandsList() {
        Response response = brandsApi.getBrandsList();

        assertEquals(getStatusFromResponseBody(response), HttpStatus.OK,
                "GET all BrandsList should return status code 200");

        assertFalse(response.jsonPath().getList("brands").isEmpty(),
                "list of brands should not be empty");
    }

    @Test(groups = "ApiTests",
            testName = "API 4: PUT To All Brands List")
    public void putToBrandsList() {
        Response response = brandsApi.putToBrandsList("{\"brand\": \"Nike\"}");

        assertEquals(getStatusFromResponseBody(response), HttpStatus.NOT_SUPPORTED,
                "PUT on all ProductsList is not supported, so should return status code 405");

        assertEquals(response.jsonPath().getString("message"),
                "This request method is not supported.");

    }
}
