package _12_API_Chaining;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class APIChainingDemo2 {
    static int id;
    static HashMap<String, Object> postData = new HashMap<>();
    static String tokenValue="cffa5fa2ec96589b0ed0946ff6f9a8993bb8079039817f319e3983afa4083d26";

    public static void main(String[] args) {
        postReqChaining();
        getReqChaining();
        //simpleGetCall();
    }
    public static void postReqChaining() {
        postData.put("name", "Julie Chan");
        postData.put("email", "julie.t1@test.com");
        postData.put("gender", "female");
        postData.put("status", "active");

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
        Response response =  given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenValue)
                .pathParams("id", id)
                .when()
                .log()
                .all()
                .get("https://gorest.co.in/public/v2/users/{id}");
                response.then()
                .statusCode(200)
                .log()
                .body();

        System.out.println("Get Request is Completed");

        //Validate the GET response against the POST payload
        String Name = response.jsonPath().getString("name");
        String Email = response.jsonPath().getString("email");
        String Gender = response.jsonPath().getString("gender");
        String Status = response.jsonPath().getString("status");

        Assert.assertEquals(postData.get("name"), Name);
        Assert.assertEquals(postData.get("email"), Email);
        Assert.assertEquals(postData.get("gender"), Gender);
        Assert.assertEquals(postData.get("status"), Status);

        System.out.println("Validation successful: GET response matches POST payload");
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
