package _10_Logs;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class LogsDemo {
    public static void main(String[] args) {
        //LogNormal();
        LogConditional();
    }
    private static void LogNormal() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 5)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/{id}")
                .then()
                .statusCode(200)
                .log().all();
//                .log().body();
//                .log().everything();
//                .log().headers();
//                .log().status();
    }

    private static void LogConditional() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 5)
                .when()
                .log()
                .ifValidationFails()
                .get("https://jsonplaceholder.typicode.com/posts/{id}")
                .then()
                .statusCode(201);
                //.log()
                //.ifStatusCodeIsEqualTo(200);
    }
}
