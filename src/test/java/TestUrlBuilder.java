import Utility.UrlBuilder;
import org.testng.annotations.Test;

public class TestUrlBuilder {

    @Test
    public void buildUrl(){
        String url = new UrlBuilder()
                .withProtocol("http")
                .withHost("localhost")
                .withPort(8080)
                .withEndpoint("api/v1/users")
                .build();
        System.out.println("*******"+url);
    }

}
