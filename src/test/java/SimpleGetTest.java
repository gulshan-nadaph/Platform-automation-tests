import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SimpleGetTest {
    private static ExtentReports extent;

    @BeforeSuite
    public void setup() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    @Test
    public void makeSureThatAPIIsUp() {
        ExtentTest test = extent.createTest("makeSureThatAPIIsUp");

        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        int statusCode = response.getStatusCode();
        try {
            Assert.assertEquals(statusCode, 200);
            test.pass("Received 200 status code as expected.");
        } catch (AssertionError e) {
            test.fail(e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        extent.flush();
    }
}