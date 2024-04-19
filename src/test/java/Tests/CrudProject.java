package Tests;

import ProfileCrud.GetProfile;
import ProjectCrud.DeleteProject;
import ProjectCrud.GetProject;
import ProjectCrud.PostProject;
import Utility.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrudProject extends BaseTest {
    @Parameters({"orgId","projectId"})
    @Test
    public void testGetProject(String orgId, String projectId) {
        PostProject postProject = new PostProject();
       String project_id =  postProject.postProject(accessToken);
       GetProject getProject = new GetProject();
        getProject.getProjects(project_id,accessToken);
        DeleteProject deleteProject = new DeleteProject();
        deleteProject.deleteProject(project_id,accessToken);

    }
}
