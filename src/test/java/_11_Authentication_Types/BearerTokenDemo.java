package _11_Authentication_Types;

import io.restassured.http.ContentType;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BearerTokenDemo {
    public static void main(String[] args) {
        BearerToken();
    }
        private static void BearerToken() {
            String tokenValue="cffa5fa2ec96589b0ed0946ff6f9a8993bb8079039817f319e3983afa4083d26";
            HashMap<String, Object> postData = new HashMap<>();
            postData.put("name", "Sam Curran");
            postData.put("email", "sam.c1@test.com");
            postData.put("gender", "Male");
            postData.put("status", "Active");

            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer " + tokenValue)
                    .body(postData)
                    .when()
                    .post("https://gorest.co.in/public/v2/users")
                    .then()
                    .statusCode(201)
                    .log()
                    .body();
    }
}
