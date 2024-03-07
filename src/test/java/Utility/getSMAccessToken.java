package Utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class getSMAccessToken {

    private String accessToken;

    public void getToken() {
        RestAssured.baseURI = Utility.GetBaseUrl.getBaseURI();
        String requestBody = "{\n" +
                "  \"user\": \"gulshan.nadaph@bentley.com\",\n" +
                "  \"password\": \"Shakila@1234\"\n" +
                "}";

        Response response = given()
                .header("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8")
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .header("Origin", "https://app.sensemetrics.com")
                .header("Referer", "https://app.sensemetrics.com/docs/index.html")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-origin")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
                .header("accept", "application/json")
                .header("sec-ch-ua", "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Google Chrome\";v=\"122\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"macOS\"")
                .body(requestBody)
                .when()
                .post("/api/auth/token")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(response.asString());
        this.accessToken = jsonPath.getString("accessToken");

        System.out.println("Access Token: " + this.accessToken);
    }

    public void anotherTest() {
        // Use this.accessToken
    }
    public String getAccessToken() {
        return this.accessToken;
    }
    public static void main(String[] args) {
        getSMAccessToken instance = new getSMAccessToken();
        instance.getToken();
        instance.anotherTest();
    }
}