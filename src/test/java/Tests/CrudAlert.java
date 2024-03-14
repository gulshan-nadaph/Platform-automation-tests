package Tests;


import AlertCrud.DeleteAlert;
import AlertCrud.PatchAlert;
import AlertCrud.PostAlert;
import Utility.BaseTest;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;


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
