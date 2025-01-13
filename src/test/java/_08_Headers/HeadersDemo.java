package _08_Headers;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class HeadersDemo {
    public static void main(String[] args) {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200);
        Headers headers = response.extract().headers();
        System.out.println(headers);
        String contentType = headers.getValue("Content-Type");
        Assert.assertEquals(contentType, "application/json; charset=utf-8");
        System.out.println("*** Validation successful: Content-Type is "+contentType+" ***");
    }
}
