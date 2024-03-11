package Tests;

import AlertCrud.PostAlert;
import ProfileCrud.GetProfile;
import Utility.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrudProfile extends BaseTest {
    @Parameters("projectId")
    @Test
    public void testGetProfile(String projectId) {
        PostAlert alertPoster = new PostAlert();
        alertPoster.PostAlert(projectId, accessToken);
        String alertId = alertPoster.getAlertId();
        GetProfile getProfile = new GetProfile();
        getProfile.testProfileGetter(projectId, accessToken);

    }
}
