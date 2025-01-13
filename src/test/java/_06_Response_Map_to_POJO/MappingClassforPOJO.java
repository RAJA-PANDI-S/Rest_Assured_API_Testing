package _06_Response_Map_to_POJO;
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

            int userId = pojoVariable.getUserId();
            Assert.assertEquals(userId, 1);
            System.out.println("UserID: "+pojoVariable.getUserId());
            System.out.println("Id: "+pojoVariable.getId());
            System.out.println("Title: "+pojoVariable.getTitle());
            System.out.println("Body: "+pojoVariable.getBody());

            System.out.println("\nUserID value is: " + userId);
            System.out.println("UserID validation is successful");
            }
        }

