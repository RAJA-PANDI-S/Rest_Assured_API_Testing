package _06_Response_Map_to_POJO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class MappingClassforPOJO {

        public static void main(String[] args) {
            ValidatableResponse response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("https://jsonplaceholder.typicode.com/posts/1")
                    .then()
                    .statusCode(200);

            // Map response to POJO
            POJOClass pojoVariable = response.extract().body().as(POJOClass.class);

            // Extract and validate userId
            int userId = pojoVariable.getUserId();
            Assert.assertEquals(userId, 1, "User ID does not match!");

            // Print results
            System.out.println("User ID = " + userId);
            System.out.println("User ID validation is successful");
            }
        }

