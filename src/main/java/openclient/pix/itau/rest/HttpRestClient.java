package openclient.pix.itau.rest;

import java.net.SocketException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import openclient.pix.itau.rest.exceptions.HttpRestClientException;

public class HttpRestClient extends AbstractRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRestClient.class);

    private HttpClient httpClient = null;

    protected HttpRestClient(RequestConfig requestConfig, ObjectMapper objectMapper, boolean ignoreSSLErrors, List<Header> defaultHeaders) {
        super(objectMapper);
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setDefaultHeaders(defaultHeaders).setDefaultRequestConfig(requestConfig);

        if (ignoreSSLErrors) {
            SSLConnectionSocketFactory sslsf = null;
            SSLContextBuilder builder = new SSLContextBuilder();
            try {
                builder.loadTrustMaterial(null, new TrustAllStrategy());
                sslsf = new SSLConnectionSocketFactory(builder.build(), new String[] {"TLSv1.2"}, null,
                        NoopHostnameVerifier.INSTANCE);
                httpClientBuilder.setSSLSocketFactory(sslsf);
            } catch (Exception e) {
                LOGGER.error("Não foi possível configurar o SSL para ignorar erros!", e);
            }
        }

        this.httpClient = httpClientBuilder.build();
    }

    private <T> RestResponse<T> execute(HttpRequestBase httpRequest, Class<T> clazz)
            throws HttpRestClientException {
        HttpResponse httpResponse = null;
        try {
            LOGGER.info("Executando requisição '{}'.", httpRequest.getRequestLine());
            httpResponse = httpClient.execute(httpRequest);
            handleHttpResponse(httpResponse);
            T result = null;
            if (clazz != null) {
                // A requisicao pode nao ter resposta.
                HttpEntity entity = httpResponse.getEntity();
                String jsonResponse = EntityUtils.toString(entity);
                LOGGER.trace("Deserializando JSON '{}' : {}", clazz.getName(), jsonResponse);
                result = deserialize(jsonResponse, clazz);
            }
            RestResponse<T> response = new RestResponse<T>();
            response.setRestObject(result);
            response.setHttpResponse(httpResponse);
            return response;

        } catch (ConnectTimeoutException e) {
            throw new HttpRestClientException("Não foi possível se conectar à URL '" + httpRequest.getURI().toString() + "'!", e);
        } catch (SocketException e) {
            throw new HttpRestClientException("Não foi possível se conectar à URL '" + httpRequest.getURI().toString() + "'!", e);
        } catch (HttpRestClientException e) {
            throw e;
        } catch (Exception e) {
            throw new HttpRestClientException("Erro ao executar requisição: " + e.getMessage(), e);
        }
    }

    public <T> T get(HttpRequestParameters httpRequestParameters, Class<T> responseClass, String encoding)
            throws HttpRestClientException {
        RestResponse<T> response = doRequest(new HttpGet(), httpRequestParameters, responseClass, encoding);
        return response.getRestObject();
    }

    public <T> T post(HttpRequestParameters httpRequestParameters, Class<T> responseClass, String encoding)
            throws HttpRestClientException {
        RestResponse<T> response = doRequest(new HttpPost(), httpRequestParameters, responseClass, encoding);
        return response.getRestObject();
    }

    public <T> RestResponse<T> execute(HttpMethod httpMethod, HttpRequestParameters httpRequestParameters, Class<T> responseClass,
                                       String encoding)
            throws HttpRestClientException {
        return doRequest(convertRequestMethod(httpMethod), httpRequestParameters, responseClass, encoding);
    }

    private <T> RestResponse<T> doRequest(HttpRequestBase httpRequest, HttpRequestParameters httpRequestParameters, Class<T> responseClass,
                                          String encoding)
            throws HttpRestClientException {
        try {
            List<HeaderParam> headers = httpRequestParameters.getHeaders();
            // seta os headers
            if (headers != null && !headers.isEmpty()) {
                ListIterator<HeaderParam> li = headers.listIterator();
                while (li.hasNext()) {
                    HeaderParam headerParam = li.next();
                    httpRequest.setHeader(headerParam.getName(), headerParam.getValue());
                }

            }
            List<QueryParam> queryParams = httpRequestParameters.getQueryParams();

            String finalUrl = httpRequestParameters.getUrl();
            // seta os query parameters
            if (queryParams != null && !queryParams.isEmpty()) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();

                ListIterator<QueryParam> li = queryParams.listIterator();
                while (li.hasNext()) {
                    QueryParam queryParam = li.next();
                    list.add(new BasicNameValuePair(queryParam.getName(), queryParam.getValue()));
                }

                URIBuilder uriBuilder = new URIBuilder(finalUrl);
                uriBuilder.addParameters(list);
                finalUrl = uriBuilder.build().toString();
            }
            // seta a URI
            httpRequest.setURI(new URI(finalUrl));

            HttpEntity httpEntity = null;

            // seta os form parameters
            List<FormParam> formParams = httpRequestParameters.getFormParams();
            if (formParams != null && !formParams.isEmpty()) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();

                ListIterator<FormParam> li = formParams.listIterator();
                while (li.hasNext()) {
                    FormParam formParam = li.next();
                    list.add(new BasicNameValuePair(formParam.getName(), formParam.getValue()));
                }

                httpEntity = new UrlEncodedFormEntity(list, encoding);
            }
            // converte o objeto JSON
            if (httpRequestParameters.getJsonObject() != null) {
                String json = serialize(httpRequestParameters.getJsonObject());
                httpEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            } else if (httpRequestParameters.getJsonString() != null) {
                httpEntity = new StringEntity(httpRequestParameters.getJsonString(), ContentType.APPLICATION_JSON);
            }
            // seta a entidade
            if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
                HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase) httpRequest;
                httpEntityEnclosingRequestBase.setEntity(httpEntity);
            }
            RestResponse<T> result = execute(httpRequest, responseClass);
            return result;
        } catch (HttpRestClientException e) {
            throw e;
        } catch (Exception e) {
            throw new HttpRestClientException("Erro ao executar requisição: " + e.getMessage(), e);
        } finally {
        }
    }

    public void close() throws Exception {
        HttpClientUtils.closeQuietly(httpClient);
        LOGGER.debug("Cliente REST HTTP finalizado.");
    }

}
