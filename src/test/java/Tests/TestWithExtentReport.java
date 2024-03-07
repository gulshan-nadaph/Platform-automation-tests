package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestWithExtentReport {
    private ExtentReports extent;
    private ExtentTest test;
    //private ExtentReports extent;




    @BeforeSuite
    public void setup() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);
        test = extent.createTest("MyTestName");
    }
    @Test
    public void testAPI() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        try {
            if (response.statusCode() == 200) {
                throw new java.lang.AssertionError("Did not receive 500 status code. Received: " + response.statusCode());
            }
             test.pass("Received 500 status code as expected.");
        } catch (java.lang.AssertionError e) {
             test.fail(e.getMessage());
            throw e; // Rethrow the error to fail the test
        }
    }





    @AfterSuite
    public void tearDown() {
        extent.flush();
    }

    // ...
}

