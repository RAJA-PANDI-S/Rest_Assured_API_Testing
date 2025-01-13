package _03_Response_Validation;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RespValidation {
    public static void main(String[] args) {
        responseValidation();
    }
    private static void responseValidation() {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .time(lessThan(2000L))
                .log()
                .body();

        System.out.println("Response Code is 200");
        System.out.println("Response Time is < 2 Secs");

        JsonPath path = new JsonPath(response.extract().body().asString());
        String userID = path.getString("userId");
        Assert.assertEquals(userID,"1");
        System.out.println("UserID value is: " + userID);
    }
}
