package AlertCrud;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DeleteAlert {

    public void deleteAlert(String alertId, String accessToken) {
        RestAssured.baseURI = "https://dev-iiot.bentley.com";
        String requestBody = "{\n" +
                "  \"ids\": [\n" +
                "    \"" + alertId + "\"\n" +
                "  ]\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+ accessToken)
                .body(requestBody)
                .log().all()
                .when()
                .delete("/api/alerts?projectIds=eb588b65-36a0-4cc5-b765-17cd31f04042")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("deleteAlert.json"))
                .extract()
                .response();

        System.out.println("Response Body: " + response.asString());
    }
}