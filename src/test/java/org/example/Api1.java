package org.example;


import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.Matcher;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.*;

public class Api1 {

    // Below  are examples of GET Method//
    @Test
    public void listUsers(){

        Response response= given()
                .when ().get("https://reqres.in/api/users?page=2");
        response.then().statusCode(200)
                .body("page",is(2))
                .body("total",is(12));


                response.prettyPrint();

    }
@Test
    public void singleUser(){
        Response response=given()
                .when().get("https://reqres.in/api/users/2");
        response.then().statusCode(200)
                .body("data.id",is(2))
                .body("data.last_name",is("Weaver"));
        response.prettyPrint();


}
@Test
public void singleUserNotFound(){
        Response response=given()
                .when().get("https://reqres.in/api/users/23");
        response.then().statusCode(404);
        response.prettyPrint();

}
@Test
public void listResource() {
    Response response = given()
            .when().get("https://reqres.in/api/unknown");
    response.then().statusCode(200)
            .body("data.id", hasItems(2))
            .body("data.color", hasItems("#C74375"))
            .body("page", is(1));
    response.prettyPrint();
}
@Test
public void singleResource(){
        Response response=given()
                .when().get("https://reqres.in/api/unknown/2");
        response.then().statusCode(200)
                .body("data.id",is(2));
                response.prettyPrint();

}
@Test
public void singleResourceNotFound(){
        Response response=given()
                .when().get("https://reqres.in/api/unknown/23");
        response.then().statusCode(404);
        response.prettyPrint();
}
@Test
public  void delayedResponse(){
        Response response=given()
                .when().get("https://reqres.in/api/users?delay=3");
        response.then().statusCode(200)
        .body("page",is(1))
                .body("data.id",hasItems(5));
                response.prettyPrint();

}

// Below are examples of POST methods //
    @Test

public void create(){
        Response response=given().contentType("application/json")
                .when().body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("https://reqres.in/api/users");

        response.then().statusCode(201)
                .body("name",is("morpheus"));
        response.prettyPrint();
        }
@Test
  public void registerSuccessful(){
        Response response=given().contentType("application/json")
                .when().body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .post("https://reqres.in/api/register");
        response.then().statusCode(200)
                .body("id",is(4));
        response.prettyPrint();

  }
  @Test
public void registerUnsuccessful(){
        Response response=given().contentType("application/json")
                .when().body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}")
                .post("https://reqres.in/api/register");
        response.then().statusCode(400)
        .body("error",is("Missing password"));
        response.prettyPrint();
}
@Test
public void loginSuccessful(){
        Response response=given().contentType("application/json")
                .when().body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("https://reqres.in/api/login");
        response.then().statusCode(200)
                .body("token",is("QpwL5tke4Pnpja7X4"));
        response.prettyPrint();
}
@Test
public void loginUnsuccessful(){
        Response response=given().contentType("application/json")

                  .when().body("{\n" +
                "    \"email\": \"peter@klaven\"\n" +                "}")
                .post("https://reqres.in/api/login");
        response.then().statusCode(400)
                .body("error",is("Missing password"));
        response.prettyPrint();
}
@Test
public void addTestRep(){
        Response response=given()
                .when().get("https://jsonplaceholder.typicode.com/posts");
        response.then().body("userId",hasItems(1));
        response.prettyPrint();
}

}
