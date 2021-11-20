package openclient.pix.itau.config;

import java.net.URL;

public class ClientConfigBuilder {

    private URL baseUrl;
    private String clientId;
    private String clientSecret;
    private int timeout;
    private boolean ignoreSSLErrors;
    private String encoding;

    private ClientConfigBuilder() {
    }

    public ClientConfigBuilder setBaseUrl(URL baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public ClientConfigBuilder setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public ClientConfigBuilder setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public ClientConfigBuilder setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public ClientConfigBuilder setIgnoreSSLErrors(boolean ignoreSSLErrors) {
        this.ignoreSSLErrors = ignoreSSLErrors;
        return this;
    }

    public ClientConfigBuilder setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public ClientConfig build() {
        if (baseUrl == null) {
            throw new IllegalArgumentException("URL base da API PIX Itaú não pode ser nula!");
        }
        String uri = baseUrl.toString();
        if (uri == null || uri.isEmpty()) {
            throw new IllegalArgumentException("URL base da API PIX Itaú não pode ser nula!");
        }
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("O ClientId da API PIX Itaú  não pode ser nulo ou vazio!");
        }
        if (clientSecret == null || clientSecret.isEmpty()) {
            throw new IllegalArgumentException("O ClientSecret da API PIX Itaú  não pode ser nulo ou vazio!");
        }

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setBaseUrl(baseUrl);
        clientConfig.setTimeout(timeout);
        clientConfig.setClientId(clientId);
        clientConfig.setClientSecret(clientSecret);
        clientConfig.setIgnoreSSLErrors(ignoreSSLErrors);
        if (encoding == null || encoding.isEmpty())
            clientConfig.setEncoding("UTF-8");
        else
            clientConfig.setEncoding(encoding);
        return clientConfig;

    }

    public static ClientConfigBuilder builder() {
        return new ClientConfigBuilder();
    }

}
