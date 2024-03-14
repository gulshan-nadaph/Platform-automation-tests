package AlertCrud;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PatchAlert {

    public void patchRequest(String projectId, String accessToken, String alertId, String metric, String unit, String criteria, int threshold, String alertType) {
        RestAssured.baseURI = "https://dev-iiot.bentley.com/api/alerts?projectIds=" + projectId;

        String requestBody = "{\"alert\":{\"props\":{\"PRIORITY\":\"NORMAL\",\"TRIGGER_MODE\":{\"type\":\"immediate\"}},\"id\":\"" + alertId + "\",\"rules\":[{\"metric\":\"" + metric + "\",\"unit\":\"" + unit + "\",\"params\":{\"dt\":{\"value\":604800}},\"criteria\":\"" + criteria + "\",\"threshold\":" + threshold + "}],\"alertType\":\"" + alertType + "\"}}";

        Response response = null;

        try {
            response = given()
                    .contentType(ContentType.JSON)
                    .accept("application/json, text/plain, */*")
                    .header("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8")
                    .header("Authorization", "Bearer "+accessToken)
                    .body(requestBody)
                    .log().all()
                    .when()
                    .patch();
            response.then().assertThat().statusCode(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}