package _01_HTTP_Methods;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
public class Q2_PostReq {
    public static void main(String args[]) {
        postReqDemo();
    }
    private static void postReqDemo() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"title\": \"foo\",\n" +
                        "    \"body\": \"bar\",\n" +
                        "    \"userId\": 1\n" +
                        "}\n")
                .when()
                .post("https://jsonplaceholder.typicode.com/posts");
                response.then()
                .statusCode(201)
                .log()
                .body();

        if (response.statusCode() == 201) {
            System.out.println("Post request was successful with Status code: 201");
        }
        // Extract the ID from the response body
        JsonPath path = new JsonPath(response.asString());
        String id = path.getString("id");
        System.out.println("New Post ID is --> " + id);
    }
}
