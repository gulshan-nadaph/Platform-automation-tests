package ProjectCrud;


import Utility.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetProject  {

    public void getProjects(String orgId, String accessToken){
        String url = "https://dev-iiot.bentley.com/api/projects?orgId="+ orgId;

        Response response = RestAssured.given()
                .header("Accept", ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("getProject.json"))
                .extract()
                .response();

        // Print the response or do something with it
        System.out.println(response.prettyPrint());
        // Print only the status code and headers
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Headers: " + response.getHeaders());

        // Print only the first 1000 characters of the body
        String body = response.getBody().asString();
        if (body.length() > 1000) {
            body = body.substring(0, 1000);
        }
        System.out.println("Body: " + body);
    }
}