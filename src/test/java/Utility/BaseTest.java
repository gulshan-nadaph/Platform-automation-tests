package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected ExtentReports extent;
    protected ExtentTest test;
    protected String metric;
    protected String unit;
    protected String criteria;
    protected int threshold;
    protected String alertType;
    protected String accessToken;
    protected String orgId;

    protected String projectId;

    @BeforeMethod
    @Parameters({"orgId", "projectId"})
    public void setUp(@Optional String orgId, @Optional String projectId){


        if (orgId == null) {
            this.orgId = System.getProperty("orgId");
        } else {
            this.orgId = orgId;
        }

        if (projectId == null) {
            this.projectId = System.getProperty("projectId");
        } else {
            this.projectId = projectId;
        }
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