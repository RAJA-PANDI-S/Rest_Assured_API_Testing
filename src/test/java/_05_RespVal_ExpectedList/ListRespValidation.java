package _05_RespVal_ExpectedList;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import static io.restassured.RestAssured.given;

public class ListRespValidation {
    public static void main(String[] args) {
        // Perform GET request
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200) // Validate the status code
                .extract()
                .response();

        // Parse response into a list
        List<Object> posts = response.jsonPath().getList("$");
        System.out.println("Total Number of posts: " + posts.size());
        Assert.assertEquals(posts.size(), 100, "The response does not contain 100 posts!");

        // Extract all userIds
        List<Integer> userIds = response.jsonPath().getList("userId", Integer.class);

        // Validate userIds are between 1 and 10
        boolean isUserIdValid = true; // Assume all userIds are valid initially
        for (Integer userId : userIds) {
            if (userId < 1 || userId > 10) {
                isUserIdValid = false;
                break;}
        }
        if (isUserIdValid) { System.out.println("All userIds are between 1 and 10."); }
        else { System.out.println("There are invalid userIds in the response."); }

        System.out.println("Validation successful: The response contains exactly 100 posts, and userIds are between 1 to 10.");
    }
}
