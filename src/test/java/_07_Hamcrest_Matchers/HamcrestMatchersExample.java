package _07_Hamcrest_Matchers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

    public class HamcrestMatchersExample {
        public static void main(String[] args) {
            validatePostWithHamcrest();
        }

        private static void validatePostWithHamcrest() {
            given()
                    .contentType("application/json")
                    .when()
                    .get("https://jsonplaceholder.typicode.com/posts/1")
                    .then()
                    .statusCode(200)
                    .body("title", startsWithIgnoringCase("s"))
                    .body("body", containsString("quia"))
                    .log()
                    .body();
            System.out.println("*** Validation Successful!");
            System.out.println("*** Title starts with 's' and body contains 'quia' ***");
        }
    }

