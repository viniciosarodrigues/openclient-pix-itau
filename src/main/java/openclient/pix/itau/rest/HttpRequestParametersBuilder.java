package openclient.pix.itau.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import openclient.pix.itau.rest.exceptions.HttpRestClientException;

public class HttpRequestParametersBuilder {
    private String url;
    private String jsonString;
    private Object jsonObject;
    private List<HeaderParam> headers;
    private List<FormParam> formParams;
    private List<QueryParam> queryParams;

    private HttpRequestParametersBuilder() {
    }

    public HttpRequestParametersBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpRequestParametersBuilder setJsonString(String jsonString) {
        this.jsonString = jsonString;
        return this;
    }

    public HttpRequestParametersBuilder setJsonObject(Object jsonObject) {
        this.jsonObject = jsonObject;
        return this;
    }

    public HttpRequestParametersBuilder addHeader(String name, String value) {
        if (headers == null) {
            headers = new ArrayList<HeaderParam>();
        }
        headers.add(new HeaderParam(name, value));
        return this;
    }

    public HttpRequestParametersBuilder addFormParam(String name, String value) {
        if (formParams == null) {
            formParams = new ArrayList<FormParam>();
        }
        formParams.add(new FormParam(name, value));
        return this;
    }

    public HttpRequestParametersBuilder addQueryParam(String name, String value) {
        if (queryParams == null) {
            queryParams = new ArrayList<QueryParam>();
        }
        queryParams.add(new QueryParam(name, value));
        return this;
    }

    public HttpRequestParametersBuilder addQueryParams(List<QueryParam> q) {
        if (this.queryParams == null) {
            this.queryParams = new ArrayList<QueryParam>();
        }
        queryParams.addAll(q);
        return this;
    }

    public HttpRequestParameters build() throws HttpRestClientException {
        if (isBlank(url)) {
            throw new HttpRestClientException("É necessário indicar a URL da requisição!");
        }
        if (jsonObject != null && isNotBlank(jsonString)) {
            throw new HttpRestClientException("Não é possível enviar a String JSON em conjunto com o Objeto JSON!");
        }
        HttpRequestParameters httpRequestParameters = new HttpRequestParameters();
        httpRequestParameters.setQueryParams(queryParams);
        httpRequestParameters.setFormParams(formParams);
        httpRequestParameters.setHeaders(headers);
        httpRequestParameters.setJsonObject(jsonObject);
        httpRequestParameters.setJsonString(jsonString);
        httpRequestParameters.setUrl(url);
        return httpRequestParameters;
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    private static final boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static HttpRequestParametersBuilder builder() {
        return new HttpRequestParametersBuilder();
    }

    public static URI getFinalURI(String url, List<QueryParam> queryParams) throws HttpRestClientException {
        if (url == null) {
            throw new NullPointerException("URL nula!");
        }
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (queryParams != null && !queryParams.isEmpty()) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();

                ListIterator<QueryParam> li = queryParams.listIterator();
                while (li.hasNext()) {
                    QueryParam queryParam = li.next();
                    list.add(new BasicNameValuePair(queryParam.getName(), queryParam.getValue()));
                }

                uriBuilder.addParameters(list);
            }
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new HttpRestClientException("URL inválida: " + url + "!", e);
        }
    }
}
