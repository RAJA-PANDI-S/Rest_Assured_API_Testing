package _02_Payload_Types;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;

public class PayloadAsJSONObject {

        public static void main(String[] args) {
            sendPayloadAsJsonObject();
        }

        private static void sendPayloadAsJsonObject() {
            JSONObject postData = new JSONObject();
            postData.put("userID", "103");
            postData.put("title", "Payload_3");
            postData.put("body", "This is an example for Payload using JSONObject");

            given()
                    .contentType(ContentType.JSON)
                    .body(postData.toString())
                    .when()
                    .post("https://jsonplaceholder.typicode.com/posts")
                    .then()
                    .statusCode(201)
                    .log()
                    .body();
        }
    }

