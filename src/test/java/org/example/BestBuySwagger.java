package org.example;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.Matcher;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.*;


public class BestBuySwagger {
@Test
    public void ProductGet(){
        Response response=given()
                .when().get("http://localhost:3030/products");

        response.then().statusCode(200)
        .body("data.id",hasItems(43900));
        response.prettyPrint();

    }
}
