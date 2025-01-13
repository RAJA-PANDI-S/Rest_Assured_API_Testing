package _11_Authentication_Types;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class BasicAuthDemo {
    public static void main(String[] args) {
        BasicAuth();
    }
    private static void BasicAuth() {
            ValidatableResponse response = given()
                    .contentType(ContentType.JSON)
                    .auth().basic("postman", "password")
                    .when()
                    .get("https://postman-echo.com/basic-auth")
                    .then()
                    .statusCode(200)
                    .log().body();

            JsonPath res = new JsonPath(response.extract().body().asString());
            String auth = res.getString("authenticated");
            Assert.assertEquals(auth, "true");
            System.out.println("\n*** Authentication completed successfully ***");
        }
    }
