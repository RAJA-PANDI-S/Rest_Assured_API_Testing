package _01_HTTP_Methods;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class GetRequest {

    public static void main(String args[]){
        GetReqDemo();
    }

    public static void GetReqDemo(){
        // Set the request content type to JSON
        given().contentType(ContentType.JSON)
                // Perform the GET request
                .when().get("https://reqres.in/api/users/2")
                // Check if the status code is 200
                .then().statusCode(200)
                // Log the response body
                .log().body();
                //.log().all();
    }
}
