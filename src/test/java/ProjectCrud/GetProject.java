package ProjectCrud;

import Utility.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.config.HttpClientConfig;

import java.io.PrintWriter;
import java.io.IOException;

import org.apache.http.params.CoreConnectionPNames;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetProject  {

    public void getProjects(String orgId, String accessToken){
        String url = "https://dev-iiot.bentley.com/api/projects?orgId="+ orgId;

        RestAssured.config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000).setParam(CoreConnectionPNames.SO_TIMEOUT, 10000));

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

        // Write the response to a file
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            writer.println(response.prettyPrint());
            writer.println("Status code: " + response.getStatusCode());
            writer.println("Headers: " + response.getHeaders());

            // Write the first 100 lines of the body
            String body = response.getBody().asString();
            String[] lines = body.split("\n");
            for (int i = 0; i < lines.length && i < 100; i++) {
                writer.println(lines[i]);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
