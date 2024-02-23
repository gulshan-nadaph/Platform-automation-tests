import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetTest {

    @Test
    public void makeSureThatAPIIsUp() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}