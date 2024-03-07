package AlertCRUD;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.http.ContentType;

public class PostAlert {

    private String alertId;

    public void PostAlert(String projectId, String accessToken) {

        String requestBody = "{\n" +
                "    \"alert\": {\n" +
                "        \"props\": {\n" +
                "            \"BLACKLIST\": [],\n" +
                "            \"SENSOR_IDS\": [\n" +
                "                \"/dev/FE4492/node/demo/2CB447/device/demo1/sensor\"\n" +
                "            ],\n" +
                "            \"AUTO_ACKNOWLEDGE\": false,\n" +
                "            \"TRIGGER_FREQUENCY\": \"ONCE\",\n" +
                "            \"STATE\": \"ACTIVE\",\n" +
                "            \"PRIORITY\": \"NORMAL\",\n" +
                "            \"TRIGGER_MODE\": {\n" +
                "                \"type\": \"immediate\"\n" +
                "            },\n" +
                "            \"NAME\": \"kk\"\n" +
                "        },\n" +
                "        \"rules\": [\n" +
                "            {\n" +
                "                \"metric\": \"dT\",\n" +
                "                \"unit\": \"C\",\n" +
                "                \"params\": {},\n" +
                "                \"criteria\": \"ABOVE\",\n" +
                "                \"threshold\": 0\n" +
                "            }\n" +
                "        ],\n" +
                "        \"alertType\": \"data\",\n" +
                "        \"allOrNone\": false\n" +
                "    }\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+ accessToken)
                .body(requestBody)
                .log().all()
                .when()
                .post("/api/alerts?projectIds=" + projectId)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("postAlert.json"))
                .extract()
                .response();

        System.out.println("Response Body: " + response.asString());
        JsonPath jsonPath = response.jsonPath();
        this.alertId = jsonPath.getString("alert.id");
        System.out.println("Alert ID: " + alertId);
    }

    public String getAlertId() {
        return alertId;
    }
}
