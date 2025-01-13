package _09_Cookies;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CookiesDemo {
    public static void main(String[] args) {
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://www.google.com/")
                .then()
                .statusCode(200);
        System.out.println("Cookies of google site: "+ response.extract().cookies());
        System.out.println("\nCookie of Key NID is: "+ response.extract().cookie("NID"));
    }
}
