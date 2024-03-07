package Tests;


import Utility.getSMAccessToken;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class CreateDevice {
    String accessToken;
    private String alertId;
    private ExtentReports extent;
    private ExtentTest test;
    //private ExtentReports extent;

    @BeforeSuite
    public void setup() {

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);
        test = extent.createTest("MyTestName");
        getSMAccessToken instance = new getSMAccessToken();
        instance.getToken();
        accessToken = instance.getAccessToken();
        RestAssured.baseURI = Utility.GetBaseUrl.getBaseURI();
    }
    @Test
    public void createDevice() {

        String requestBody = "{\n" +
                "  \"id\": \"/dev/138510/node/demo/3CC2C7/device\",\n" +
                "  \"type\": \"VIOTEL_TILT_DEVICE\",\n" +
                "  \"props\": {},\n" +
                "  \"customProps\": {\n" +
                "    \"laboreb\": {}\n" +
                "  }\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " +accessToken)
                .body(requestBody)
                .log().all()
                .when()
                .post("https://dev-iiot.bentley.com/api/devices?projectIds=eb588b65-36a0-4cc5-b765-17cd31f04042")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        System.out.println("Response Body: " + response.asString());
        JsonPath jsonPath = response.jsonPath();
        System.out.println("***********"+jsonPath.toString());
        alertId = jsonPath.getString("id"); // replace "id" with the actual key for the alert ID in the response
    }

    @AfterTest
    public void deleteDevice() {
        DeleteDevice deleteAlert = new DeleteDevice();
        deleteAlert.deleteDevice(this.alertId, this.accessToken);
    }
    @AfterSuite
    public void tearDown() {
        extent.flush();
    }
}
