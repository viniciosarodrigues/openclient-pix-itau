package openclient.pix.itau.rest.exceptions;

/**
 * Erro HTTP
 * 
 * @author viniciosarodrigues
 *
 */
public class HttpRestClientException extends Exception {

    private static final long serialVersionUID = 1753043056877541829L;

    private int httpStatusCode;
    private String httpBody;

    public HttpRestClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpRestClientException(String message) {
        super(message);
    }

    public HttpRestClientException(String message, String httpBody, int httpStatusCode) {
        super(message);
        this.setHttpBody(httpBody);
        this.setHttpStatusCode(httpStatusCode);
    }

    public String getHttpBody() {
        return httpBody;
    }

    public void setHttpBody(String httpBody) {
        this.httpBody = httpBody;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
