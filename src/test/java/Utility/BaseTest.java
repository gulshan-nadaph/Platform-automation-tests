package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected ExtentReports extent;
    protected ExtentTest test;
    protected String accessToken;
    protected String metric;
    protected String unit;
    protected String criteria;
    protected int threshold;
    protected String alertType;

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
        // Initialize the other variables
        metric = "dT";
        unit = "C";
        criteria = "ABOVE";
        threshold = 100; // your_threshold
        alertType = "data";
    }
}