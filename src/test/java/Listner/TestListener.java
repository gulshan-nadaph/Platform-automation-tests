package Listner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;


public class TestListener implements ITestListener {
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Implement other methods as needed...
}