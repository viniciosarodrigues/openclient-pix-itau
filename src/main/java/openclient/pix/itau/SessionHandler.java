package openclient.pix.itau;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import openclient.pix.itau.config.ClientConfig;
import openclient.pix.itau.rest.AuthenticationResponse;
import openclient.pix.itau.rest.HttpMethod;
import openclient.pix.itau.rest.HttpRequestParameters;
import openclient.pix.itau.rest.HttpRequestParametersBuilder;
import openclient.pix.itau.rest.HttpRestClient;
import openclient.pix.itau.rest.HttpRestClientBuilder;
import openclient.pix.itau.rest.QueryParam;
import openclient.pix.itau.rest.RestResponse;
import openclient.pix.itau.rest.exceptions.HttpRestClientException;

/**
 * Tratador de sessão de acesso à API
 * 
 * @author viniciosarodrigues
 *
 */
public class SessionHandler {

    private static final String CLIENT_SECRET_KEY = "client_secret";
    private static final String CLIENT_ID_KEY = "client_id";
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionHandler.class);
    private static final String AUTHORIZATION = "Authorization";
    private static final String GRANT_TYPE_KEY = "grant_type";
    private static final String GRANT_TYPE_VALUE = "client_credentials";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String CONTENT_TYPE_HEADER_VALUE = "application/x-www-form-urlencoded";

    private HttpRestClient restClient;
    private String currentAuthenticationToken;
    private ClientConfig clientConfig;
    private ObjectMapper objectMapper;
    private boolean closed = false;
    private String baseUrl = null;

    public String getClientId() {
        return clientConfig.getClientId();
    }

    protected SessionHandler(ClientConfig clientConfig) {
        this.objectMapper = createObjectMapper();
        HttpRestClientBuilder builder = HttpRestClientBuilder.create().setObjectMapper(objectMapper)
                .ignoreSSLErrors(clientConfig.isIgnoreSSLErrors());
        if (clientConfig.getTimeout() > -1) {
            builder.setDefaultTimeout(clientConfig.getTimeout());
        }
        this.restClient = builder.build();
        this.baseUrl = clientConfig.getBaseUrl().toString();
        this.clientConfig = clientConfig;
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);
        return mapper;
    }

    public void signin() throws HttpRestClientException {
        if (isClosed()) {
            throw new HttpRestClientException("Pix Itaú Client finalizado!");
        }

        String signinUrl = this.baseUrl + "/api/oauth/token";

        try {
            HttpRequestParametersBuilder httpRequestParametersBuilder = HttpRequestParametersBuilder.builder()
                    .setUrl(signinUrl).addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_HEADER_VALUE)
                    .addFormParam(GRANT_TYPE_KEY, GRANT_TYPE_VALUE)
                    .addFormParam(CLIENT_ID_KEY, this.clientConfig.getClientId())
                    .addFormParam(CLIENT_SECRET_KEY, this.clientConfig.getClientSecret());

            HttpRequestParameters httpRequestParameters = httpRequestParametersBuilder.build();
            HttpRestClientBuilder builder = HttpRestClientBuilder.create()
                    .setObjectMapper(objectMapper)
                    .ignoreSSLErrors(this.clientConfig.isIgnoreSSLErrors());
            if (this.clientConfig.getTimeout() > -1) {
                builder.setDefaultTimeout(this.clientConfig.getTimeout());
            }

            RestResponse<AuthenticationResponse> restResponse = builder.build().execute(HttpMethod.POST,
                                                                                        httpRequestParameters,
                                                                                        AuthenticationResponse.class,
                                                                                        this.clientConfig.getEncoding());

            this.currentAuthenticationToken = "Bearer ".concat(restResponse.getRestObject().getAccessToken());
            LOGGER.info("Usuário '{}' autenticado com sucesso!", this.clientConfig.getClientId());
        } catch (Exception e) {
            if (e instanceof HttpRestClientException)
                throw e;
            throw new HttpRestClientException("Erro ao efetuar login: " + e.getMessage(), e);
        }

    }

    public <T> T post(String relativeUrl, Object jsonObject, Class<T> responseClass) throws HttpRestClientException {
        this.checkStatus();
        try {
            String finalUrl = this.baseUrl + relativeUrl;
            String jsonString = serialize(jsonObject);
            LOGGER.info(jsonString);

            HttpRequestParameters httpRequestParameters = HttpRequestParametersBuilder.builder().setUrl(finalUrl)
                    .addHeader(AUTHORIZATION, currentAuthenticationToken).setJsonString(jsonString).build();

            RestResponse<T> restResponse = restClient.execute(HttpMethod.POST, httpRequestParameters, responseClass,
                                                              this.clientConfig.getEncoding());
            return restResponse.getRestObject();
        } catch (HttpRestClientException e) {
            if (e.getHttpStatusCode() == 401) {
                updateToken();
                return post(relativeUrl, jsonObject, responseClass);
            }
            throw e;
        } catch (Exception e) {
            if (e instanceof HttpRestClientException)
                throw e;
            throw new HttpRestClientException("Erro interno ao efetuar requisição ao Pix Itau: " + e.getMessage(),
                    e);
        }

    }

    public <T> T put(String relativeUrl, Object jsonObject, Class<T> responseClass) throws HttpRestClientException {
        this.checkStatus();
        try {
            String finalUrl = this.baseUrl + relativeUrl;
            String jsonString = serialize(jsonObject);
            LOGGER.info(jsonString);

            HttpRequestParameters httpRequestParameters = HttpRequestParametersBuilder.builder().setUrl(finalUrl)
                    .addHeader(AUTHORIZATION, currentAuthenticationToken).setJsonString(jsonString).build();

            RestResponse<T> restResponse = restClient.execute(HttpMethod.PUT, httpRequestParameters, responseClass,
                                                              this.clientConfig.getEncoding());
            return restResponse.getRestObject();
        } catch (HttpRestClientException e) {
            if (e.getHttpStatusCode() == 401) {
                updateToken();
                return put(relativeUrl, jsonObject, responseClass);
            }
            throw e;
        } catch (Exception e) {
            if (e instanceof HttpRestClientException)
                throw e;
            throw new HttpRestClientException("Erro interno ao efetuar requisição ao Pix Itau: " + e.getMessage(),
                    e);
        }

    }

    public <T> T get(String relativeUrl, List<QueryParam> queryParams, Class<T> responseClass)
            throws HttpRestClientException {
        this.checkStatus();
        try {
            String url = this.baseUrl + relativeUrl;
            String finalUrl = HttpRequestParametersBuilder.getFinalURI(url, queryParams).toString();
            HttpRequestParameters httpRequestParameters = HttpRequestParametersBuilder.builder().setUrl(finalUrl)
                    .addHeader(AUTHORIZATION, currentAuthenticationToken).build();
            RestResponse<T> restResponse = restClient.execute(HttpMethod.GET, httpRequestParameters, responseClass,
                                                              this.clientConfig.getEncoding());

            return restResponse.getRestObject();
        } catch (HttpRestClientException e) {
            if (e.getHttpStatusCode() == 401 && e.getHttpBody().contains("invalid_token")) {
                updateToken();
                return get(relativeUrl, queryParams, responseClass);
            }
            throw e;
        } catch (Exception e) {
            throw new HttpRestClientException(
                    "Erro interno ao efetuar requisição ao Pix Itau Webservices: " + e.getMessage(), e);
        }

    }

    private String serialize(Object object) throws HttpRestClientException {
        try {
            String jsonString = this.objectMapper.writeValueAsString(object);
            LOGGER.info("Objeto '{}'  : {}", object.getClass().getName(), jsonString);
            return jsonString;
        } catch (Exception e) {
            throw new HttpRestClientException("Erro ao serializar JSON: " + e.getMessage(), e);
        }
    }

    private void updateToken() throws HttpRestClientException {
        if (!isClosed()) {
            signin();
        }
    }

    /**
     * Finaliza a sessão e fecha os recursos de rede.
     */
    public void close() {
        closed = true;
        if (this.restClient != null) {
            try {
                this.restClient.close();
            } catch (Exception e) {
                LOGGER.error("Falha ao tentar encerrar o client", e);
            }
        }
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isSignedIn() {
        return this.currentAuthenticationToken != null;
    }

    private void checkStatus() throws HttpRestClientException {
        if (isClosed()) {
        } else if (!isSignedIn()) {
            throw new HttpRestClientException("Usuário não logado!");
        }
    }
}
