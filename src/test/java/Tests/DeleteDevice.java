package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteDevice {

    @Test
    public void deleteDevice(String deviceId,String accessToken) {
        RestAssured.baseURI = Utility.GetBaseUrl.getBaseURI();

        Response response = given()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer "+ accessToken)
                .contentType(ContentType.JSON)
                .delete("/api/devices/device?projectIds=eb588b65-36a0-4cc5-b765-17cd31f04042");

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}