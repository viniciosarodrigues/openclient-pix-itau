package openclient.pix.itau.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpRestClientBuilder {

    private ObjectMapper objectMapper;
    private Header authHeader;
    private List<Header> defaultHeaders = new ArrayList<Header>();
    // default timeout: 15 segundos
    private int defaultTimeout = 15000;
    private boolean ignoreSSLErrors;

    private HttpRestClientBuilder() {
    }

    public static HttpRestClientBuilder create() {
        return new HttpRestClientBuilder();
    }

    public HttpRestClient build() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(this.defaultTimeout)
                .setConnectTimeout(this.defaultTimeout)
                .setSocketTimeout(this.defaultTimeout)
                .build();
        ObjectMapper om = this.objectMapper != null ? this.objectMapper : new ObjectMapper();
        if (authHeader != null) {
            defaultHeaders.add(authHeader);
        }
        return new HttpRestClient(requestConfig, om, this.ignoreSSLErrors, defaultHeaders);
    }

    public HttpRestClientBuilder setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        return this;
    }

    public HttpRestClientBuilder addDefaultHeader(String headerKey, String headerValue) {
        this.defaultHeaders.add(new BasicHeader(headerKey, headerValue));
        return this;
    }

    public HttpRestClientBuilder ignoreSSLErrors(boolean ignoreSSLErrors) {
        this.ignoreSSLErrors = ignoreSSLErrors;
        return this;
    }

    public HttpRestClientBuilder setDefaultTimeout(int defaultTimeout) {
        if (defaultTimeout < 1) {
            throw new IllegalArgumentException("Timeout inválido: " + defaultTimeout);
        }
        this.defaultTimeout = defaultTimeout;
        return this;
    }

}
