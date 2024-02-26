package Utility;

public class UrlBuilder {

    private String protocol;
    private String host;
    private int port;
    private String endpoint;

    public UrlBuilder withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public UrlBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public UrlBuilder withPort(int port) {
        this.port = port;
        return this;
    }

    public UrlBuilder withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public String build() {
        return protocol + "://" + host + ":" + port + "/" + endpoint;
    }
}