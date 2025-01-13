package _12_API_Chaining;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class APIChainingDemo {
    static int id;
    public static void main(String[] args) {
        postReqChaining();
        getReqChaining();
    }
    public static void postReqChaining() {
        JSONObject postData = new JSONObject();
        postData.put("userId", "123");
        postData.put("title", "API Chaining");
        postData.put("body", "This is an example for API Chaining Post Request");

       ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .body(postData.toString())
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log()
                .body();

        JsonPath userId = new JsonPath(response.extract().body().asString());
        id = userId.getInt("id");
        System.out.println("Generated Post ID is --> " + id);
    }

    public static void getReqChaining() {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .pathParams("id", id)
                .when()
                .log()
                .all()
                .get("https://jsonplaceholder.typicode.com/posts/{id}")
                .then()
                .statusCode(404)
                .log()
                .body();
        System.out.println("Response Code is 200");
        System.out.println("get Request is successful");
    }
}
