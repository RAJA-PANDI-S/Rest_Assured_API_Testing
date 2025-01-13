package _04_JSONSchema_Validator;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SchemaValidation {
    public static void main(String[] args) {
        jsonSchemaValidation();
    }
    private static void jsonSchemaValidation() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/TestData/jsonSchema.json")));
        System.out.println("JSON Schema Validation was successful");
    }
}
