package CRUDOperations;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

public class GetRequest {

    public static void main(String args[]){
        GetReqDemo();
    }

//<<<<<<<<<<<<<<  ✨ Codeium Command 🌟 >>>>>>>>>>>>>>>>
    /**
     * Simple GET request to check if the request is working as expected.
     */
    public static void GetReqDemo(){
        // Set the request content type to JSON
        given().contentType(ContentType.JSON)
                // Perform the GET request
                .when().get("https://reqres.in/api/users/2")
                // Check if the status code is 200
                .then().statusCode(200)
                // Log the response body
                .log().body();
    }
//<<<<<<<  ca4dabb8-4067-4010-8472-fa8acacbbd74  >>>>>>>

}
