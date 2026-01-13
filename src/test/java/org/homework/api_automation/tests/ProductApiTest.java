package org.homework.api_automation.tests;

import io.restassured.response.Response;
import org.homework.api_automation.apis.ProductsApi;
import org.homework.common.util.HttpStatus;
import org.homework.common.util.TestDataLoader;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductApiTest extends BaseApiTest {

    private final ProductsApi productsApi = new ProductsApi();

    @Test(groups = "ApiTests",
            testName = "API 1: Get All Products List")
    public void getAllProductsList() {
        Response response = productsApi.getProductList();
        assertEquals(getStatusFromResponseBody(response), HttpStatus.OK,
                "GET all ProductsList should return status code 200");
        assertFalse(response.jsonPath().getList("products").isEmpty(),
                "list of products should not be empty");
    }

    @Test(groups = "ApiTests",
            testName = "API 2: POST To All Products List")
    public void postToProductsList() {
        Response response = productsApi.postToProductsList(TestDataLoader.loadProductFile());

        assertEquals(getStatusFromResponseBody(response), HttpStatus.NOT_SUPPORTED,
                "POST on all ProductsList is not supported, so should return status code 405");
    }

}
