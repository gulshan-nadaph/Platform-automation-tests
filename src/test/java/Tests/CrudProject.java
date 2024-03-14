package Tests;

import ProfileCrud.GetProfile;
import ProjectCrud.GetProject;
import Utility.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrudProject extends BaseTest {
    @Parameters({"orgId"})
    @Test
    public void testGetProject(String orgId) {
        GetProject getProfile = new GetProject();
        getProfile.getProjects(orgId,accessToken);

    }
}
