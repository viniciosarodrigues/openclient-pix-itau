package openclient.pix.itau.rest;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class RestResponse<T> {

    private T restObject;
    private HttpResponse httpResponse;

    public T getRestObject() {
        return restObject;
    }

    protected void setRestObject(T resultObject) {
        this.restObject = resultObject;
    }

    protected HttpResponse getHttpResponse() {
        return httpResponse;
    }

    protected void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public String getHeader(String headerKey) {
        String headerValue = null;
        if (httpResponse != null) {
            Header header = httpResponse.getFirstHeader(headerKey);
            if (header != null) {
                headerValue = header.getValue();
            }
        }
        return headerValue;
    }
}
