import com.aventstack.extentreports.ExtentReports;
import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;

public class SchemaValidationTest {
    private static ExtentReports extent;

    @Test
    public void validateSchema() {
        extent = new ExtentReports();
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given().
                get("/posts/1").
                then().
                assertThat().
                body(matchesJsonSchemaInClasspath("schema.json"));
    }

    @AfterMethod
    public void tearDown() {
        extent.flush();
    }
}