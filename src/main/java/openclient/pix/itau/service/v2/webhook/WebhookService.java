package openclient.pix.itau.service.v2.webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openclient.pix.itau.SessionHandler;
import openclient.pix.itau.rest.RestResponse;
import openclient.pix.itau.rest.exceptions.HttpRestClientException;
import openclient.pix.itau.service.RestService;
import openclient.pix.itau.service.v2.webhook.protocol.FindWebhookByIdResponse;
import openclient.pix.itau.service.v2.webhook.protocol.RegisterWebhookRequest;

/**
 * Camada de serviços para criaçãod e Webhook
 * 
 * @author viniciosarodrigues
 *
 */
public class WebhookService extends RestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookService.class);
    private static final String SERVICE_RELATIVE_URL = "/pix_recebimentos/v2/webhook";

    public WebhookService(SessionHandler sessionHandler) {
        super(sessionHandler);
    }

    /**
     * Busca webhook registrado com a chave PIX informada
     * 
     * @param pixKey Chave PIX
     * @throws HttpRestClientException
     */
    public FindWebhookByIdResponse findByPIXKey(String pixKey) throws HttpRestClientException {
        if (pixKey == null || pixKey.isEmpty()) {
            throw new HttpRestClientException("A chave pix não pode ser nula na consulta de webhook por chave PIX", null, 400);
        }
        LOGGER.info("Efetuando busca de webhook por chave PIX no servidor :: {}", pixKey);

        FindWebhookByIdResponse response =
                                         sessionHandler.get(SERVICE_RELATIVE_URL.concat("/" + pixKey), null, FindWebhookByIdResponse.class);

        LOGGER.info("Webhook encontrado :: {}", response.getWebhookUrl());

        return response;
    }

    /**
     * Registra um webhook para uma nova chave PIX
     * 
     * @param request Requisição de registro de webhook
     * @throws HttpRestClientException
     */
    public void register(String pixKey, RegisterWebhookRequest request) throws HttpRestClientException {
        if (request == null) {
            throw new HttpRestClientException("A requisição de registro de webhook não pode ser nula", null, 400);
        }
        LOGGER.info("Efetuando registro de webhook por chave PIX no servidor do Itaú :: {}", request.getWebhookUrl());

        sessionHandler.put(SERVICE_RELATIVE_URL.concat("/" + pixKey), request, RestResponse.class);

        LOGGER.info("Webhook registrado :: {}", request.getWebhookUrl());

    }
}
