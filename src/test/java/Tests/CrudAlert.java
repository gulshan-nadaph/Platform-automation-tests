package Tests;



import AlertCRUD.DeleteAlert;
import AlertCRUD.PatchAlert;
import AlertCRUD.PostAlert;
import Utility.BaseTest;
import Utility.getSMAccessToken;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class CrudAlert extends BaseTest {
    @Parameters("projectId")
    @Test
    public void testPostAlert(String projectId) {
        PostAlert alertPoster = new PostAlert();
        alertPoster.PostAlert(projectId, accessToken);
        String alertId = alertPoster.getAlertId();
        PatchAlert alertPatcher = new PatchAlert();
        alertPatcher.patchRequest(projectId, accessToken, alertId, metric, unit, criteria, threshold, alertType);
        DeleteAlert alertDeleter = new DeleteAlert();
        alertDeleter.deleteAlert(alertId, accessToken);

    }
}
