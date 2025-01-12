package _01_HTTP_Methods;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class Q1_GetReq {
    public static void main(String args[]) {
        getReqDemo();
    }

    private static void getReqDemo() {
        // Given the Content-Type of the request to be JSON
        // When a GET request is sent to the URL
        // Then the status code should be 200
        // And log the response body
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .log()
                .body();

        JsonPath path = new JsonPath(response.extract().body().asString());
        String Title = path.getString("[0].title");
        System.out.println("Title of 1st Post is --> " + Title);
    }
}
