package openclient.pix.itau.rest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import openclient.pix.itau.rest.exceptions.HttpRestClientException;

/**
 * Client base
 * 
 * @author viniciosarodrigues
 *
 */
public class AbstractRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestClient.class);

    private ObjectMapper objectMapper;

    public AbstractRestClient(ObjectMapper objectMapper) {
        if (objectMapper == null) {
            throw new NullPointerException("Conversor de objeto JSON não utilizado.");
        }
        this.objectMapper = objectMapper;
    }

    /**
     * Método responsável por converter o objeto JSON em String.
     * 
     * @param jsonObject Objeto a ser serializado.
     * @return String JSON convertida.
     * @throws HttpRestClientException
     */
    protected String serialize(Object jsonObject)
            throws HttpRestClientException {
        try {
            String jsonString = this.objectMapper.writeValueAsString(jsonObject);
            LOGGER.trace("Objeto '{}'  : {}", jsonObject.getClass().getName(), jsonString);
            return jsonString;
        } catch (Exception e) {
            throw new HttpRestClientException("Erro ao serializar JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Método responsável por converter a String JSON em objeto.
     * 
     * @param jsonString String JSON.
     * @param responseClass Classe de resposta.
     * @return Objeto JSON convertido.
     * @throws HttpRestClientException Se não for possível converter a String em JSON.
     */
    protected <T> T deserialize(String jsonString, Class<T> responseClass)
            throws HttpRestClientException {
        try {
            return (T) this.objectMapper.readValue(jsonString, responseClass);
        } catch (Exception e) {
            LOGGER.error("Erro ao deserializar JSON: " + e.getMessage(), e);
            throw new HttpRestClientException("Erro ao deserializar JSON: " + e.getMessage(), e);
        }
    }

    protected static HttpRequestBase convertRequestMethod(HttpMethod httpMethod) throws HttpRestClientException {
        if (httpMethod == null) {
            throw new NullPointerException("Método HTTP nulo!");
        }
        switch (httpMethod) {
            case GET:
                return new HttpGet();
            case POST:
                return new HttpPost();
            case PUT:
                return new HttpPut();
            case DELETE:
                return new HttpDelete();
            case HEAD:
                return new HttpHead();
            case OPTIONS:
                return new HttpOptions();
            default:
                throw new HttpRestClientException("Método HTTP não implementado: " + httpMethod.name());
        }
    }

    protected static void handleHttpResponse(HttpResponse httpResponse) throws HttpRestClientException {
        StatusLine statusLine = httpResponse.getStatusLine();
        int httpCode = statusLine.getStatusCode();
        // se não for status de sucesso
        if (httpCode != HttpStatus.SC_OK && httpCode != HttpStatus.SC_NO_CONTENT && httpCode != HttpStatus.SC_CREATED) {
            HttpEntity entity = httpResponse.getEntity();
            String httpBody = null;
            if (entity != null) {
                try {
                    httpBody = EntityUtils.toString(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            switch (httpCode) {
                case HttpStatus.SC_FORBIDDEN:
                    throw new HttpRestClientException(
                            "Autorização no servidor externo falhou!", httpBody, httpCode);
                case HttpStatus.SC_BAD_REQUEST:
                    throw new HttpRestClientException(
                            "Requisição inválida!", httpBody, httpCode);
                case HttpStatus.SC_UNAUTHORIZED:
                    throw new HttpRestClientException(
                            "Autenticação no servidor externo falhou!", httpBody, httpCode);
                case HttpStatus.SC_NOT_FOUND:
                    throw new HttpRestClientException(
                            "Recurso não encontrado!", httpBody, httpCode);
                case HttpStatus.SC_SERVICE_UNAVAILABLE:
                    throw new HttpRestClientException(
                            "Serviço indisponível!", httpBody, httpCode);
                default:
                    throw new HttpRestClientException(
                            "Erro ao consultar recurso no servidor externo! Código HTTP do servidor externo: " + httpCode + ".", httpBody,
                            httpCode);
            }
        }
    }
}
