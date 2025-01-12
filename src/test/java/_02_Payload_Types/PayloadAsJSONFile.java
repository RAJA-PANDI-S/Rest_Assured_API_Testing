package _02_Payload_Types;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PayloadAsJSONFile {
            public static void main(String[] args) throws FileNotFoundException {
                sendPayloadAsJsonFile();
        }

        private static void sendPayloadAsJsonFile(){
            JSONObject postData;
            try(FileReader fileReader = new FileReader(new File("src/test/resources/TestData/postData.json"))){
                JSONTokener jsonTokener = new JSONTokener(fileReader);
                postData= new JSONObject(jsonTokener);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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