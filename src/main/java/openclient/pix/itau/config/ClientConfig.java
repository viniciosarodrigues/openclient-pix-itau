package openclient.pix.itau.config;

import java.net.URL;

public class ClientConfig {

    private URL baseUrl;
    private String clientId;
    private String clientSecret;
    private int timeout;
    private boolean ignoreSSLErrors;
    private String encoding;

    protected ClientConfig() {
        super();
    }

    protected ClientConfig(URL baseUrl, String clientId, String clientSecret, int timeout, boolean ignoreSSLErrors, String encoding) {
        super();
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.timeout = timeout;
        this.ignoreSSLErrors = ignoreSSLErrors;
        this.encoding = encoding;
    }

    public URL getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(URL baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isIgnoreSSLErrors() {
        return ignoreSSLErrors;
    }

    public void setIgnoreSSLErrors(boolean ignoreSSLErrors) {
        this.ignoreSSLErrors = ignoreSSLErrors;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientConfig [baseUrl=");
        builder.append(baseUrl);
        builder.append(", clientId=");
        builder.append(clientId);
        builder.append(", clientSecret=");
        builder.append(clientSecret);
        builder.append(", timeout=");
        builder.append(timeout);
        builder.append(", ignoreSSLErrors=");
        builder.append(ignoreSSLErrors);
        builder.append(", encoding=");
        builder.append(encoding);
        builder.append("]");
        return builder.toString();
    }

}
