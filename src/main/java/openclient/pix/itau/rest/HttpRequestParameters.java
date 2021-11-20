package openclient.pix.itau.rest;

import java.util.List;

public class HttpRequestParameters {
    private String url;
    private String jsonString;
    private Object jsonObject;
    private List<HeaderParam> headers;
    private List<FormParam> formParams;
    private List<QueryParam> queryParams;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public Object getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(Object jsonObject) {
        this.jsonObject = jsonObject;
    }

    public List<HeaderParam> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HeaderParam> headers) {
        this.headers = headers;
    }

    public List<FormParam> getFormParams() {
        return formParams;
    }

    public void setFormParams(List<FormParam> formParams) {
        this.formParams = formParams;
    }

    public List<QueryParam> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(List<QueryParam> queryParams) {
        this.queryParams = queryParams;
    }
}
