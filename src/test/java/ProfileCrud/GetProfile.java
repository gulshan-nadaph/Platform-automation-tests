package ProfileCrud;


import Utility.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetProfile extends BaseTest {

    public void testProfileGetter(String projectId, String accessToken){

        RestAssured.baseURI = "https://dev-iiot.bentley.com";

        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/api/profiles?type=dashboardView&projectIds=" + projectId)
                .then()
                .extract()
                .response();

// Print the status code and the body of the response
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body().asString());

// Then do the assertions
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("getProfile.json"));
    }
}