package ProjectCrud;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteProject {
    public void deleteProject(String project_id, String accessToken) {
        String url = "https://dev-iiot.bentley.com/api/projects/" + project_id;

        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(url)
                .then()
                .log().all()
                .extract()
                .response();

        // You can now use the response object to check the status, headers, body, etc.
        // For example:
        System.out.println("Status code: " + response.getStatusCode());
    }
}
