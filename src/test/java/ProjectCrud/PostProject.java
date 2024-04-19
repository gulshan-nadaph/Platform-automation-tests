package ProjectCrud;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostProject {

    public String postProject(String accessToken) {
        // Create JSON object for the request body
        JSONObject requestBody = new JSONObject();
        JSONObject props = new JSONObject();
        props.put("MAX_GPS", 0);
        props.put("CREATED_BY", "support");
        props.put("LAST_MODIFIED_BY", "support");
        props.put("MAX_AMTS_SENSORS", 1);
        props.put("MAX_ARRAY_SENSORS", 0);
        props.put("ASSOC_AUTH_IDS", new JSONArray());
        props.put("CREATION_DATE", "2024-03-20T09:49:36.883+00:00");
        props.put("LAST_MODIFIED_DATE", "2024-03-20T09:49:36.883+00:00");
        props.put("MAX_IMAGE", 0);
        props.put("MAX_HIGHSPEED_SENSORS", 0);
        props.put("MAX_COMPLIMENTARY_SENSORS", 0);
        props.put("NAME", "mm");
        props.put("ORGANIZATION_ID", "62A9C8CE93266458902FFF48");
        props.put("MAX_DISCRETE_SENSORS", 0);
        props.put("VISIBILITY", "RESTRICTED");
        JSONArray modules = new JSONArray();
        modules.put("ALERT");
        modules.put("ANALYZE");
        modules.put("DATA_IMPORT");
        modules.put("DEFORMATION");
        modules.put("DOCUMENTS");
        modules.put("FORMWORK");
        modules.put("MAP");
        modules.put("POLAR");
        modules.put("REPORTS");
        modules.put("SLOPE");
        props.put("MODULES", modules);
        props.put("SUSPENDED", false);
        props.put("ACCESSIBLE", true);
        props.put("ULAS_BILLING_ENABLED", true);
        props.put("MAX_MANUAL_PRISM", 0);
        props.put("MAX_VARIABLE", 0);
        props.put("MAX_MANUAL_INCLINOMETER", 0);
        props.put("MAX_COMPOUND_SENSORS", 0);
        requestBody.put("props", props);
        requestBody.put("sensorCount", 0);
        requestBody.put("userCount", 0);
        requestBody.put("defaultProject", false);

        Response response = RestAssured.given()
                .baseUri("https://dev-iiot.bentley.com")
                .basePath("/api/projects")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody.toString())
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .log().all() // This will log the response details
                .extract()
                .response();

        // Parse the response body to get the project id
        JSONObject responseBody = new JSONObject(response.asString());
        String projectId = responseBody.getString("id");
        System.out.println("*******"+projectId);

        // Return the project id
        return projectId;
    }
}