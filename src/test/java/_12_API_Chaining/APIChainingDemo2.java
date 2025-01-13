package _12_API_Chaining;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class APIChainingDemo2 {
    static int id;
    public static void main(String[] args) {
        postReqChaining();
        getReqChaining();
        //simpleGetCall();
    }
    public static void postReqChaining() {
        String tokenValue="cffa5fa2ec96589b0ed0946ff6f9a8993bb8079039817f319e3983afa4083d26";
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("name", "Karen Dillan");
        postData.put("email", "karen.w1@test.com");
        postData.put("gender", "Female");
        postData.put("status", "Active");

        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenValue)
                .body(postData)
                .when()
                .post("https://gorest.co.in/public/v2/users")
                .then()
                .statusCode(201)
                .log()
                .body();

        JsonPath userId = new JsonPath(response.extract().body().asString());
        id = userId.getInt("id");
        System.out.println("Generated Post ID is --> " +id+"\n");
    }

    public static void getReqChaining() {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .pathParams("id", id)
                .when()
                .log()
                .all()
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log()
                .body();
        System.out.println("Get Request is Completed");
    }

    public static void simpleGetCall() {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                //.pathParams("id", 7633351)
                .when()
                .log()
                .all()
                .get("https://gorest.co.in/public/v2/users")
                .then()
                .statusCode(200)
                .log()
                .body();
        System.out.println("get Request is Completed");
    }
}
