package _02_Payload_Types;

import io.restassured.http.ContentType;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class PayloadAsHashmap {
    public static void main(String[] args) {
        sendPayloadAsHashMap();
    }

    private static void sendPayloadAsHashMap() {
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("userID", "101");
        postData.put("title", "Payload_1");
        postData.put("body", "This is an example for Payload using Hashmap");

        given()
                .contentType(ContentType.JSON)
                .body(postData)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .log()
                .body();
    }
}
