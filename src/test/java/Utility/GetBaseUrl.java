package Utility;

public class GetBaseUrl {
    public static String getBaseURI() {
        String environment = System.getProperty("environment");
        if ("qa".equals(environment)) {
            return "https://qa-iiot.bentley.com";
        } else {
            // Default to dev environment
            return "https://dev-iiot.bentley.com";
        }
    }
}