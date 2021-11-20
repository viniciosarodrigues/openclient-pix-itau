package openclient.pix.itau.service.v2.webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openclient.pix.itau.SessionHandler;
import openclient.pix.itau.rest.RestResponse;
import openclient.pix.itau.rest.exceptions.HttpRestClientException;
import openclient.pix.itau.service.RestService;
import openclient.pix.itau.service.v2.webhook.protocol.BuscaWebhookResponse;
import openclient.pix.itau.service.v2.webhook.protocol.RegistraWebhookRequest;

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
    public BuscaWebhookResponse buscaPorChavePix(String chavePix) throws HttpRestClientException {
        if (chavePix == null || chavePix.isEmpty()) {
            throw new HttpRestClientException("A chave pix não pode ser nula na consulta de webhook por chave PIX", null, 400);
        }
        LOGGER.info("Efetuando busca de webhook por chave PIX no servidor :: {}", chavePix);

        BuscaWebhookResponse response =
                                      sessionHandler.get(SERVICE_RELATIVE_URL.concat("/" + chavePix), null, BuscaWebhookResponse.class);

        LOGGER.info("Webhook encontrado :: {}", response.getWebhookUrl());

        return response;
    }

    /**
     * Registra um webhook para uma nova chave PIX
     * 
     * @param request Requisição de registro de webhook
     * @throws HttpRestClientException
     */
    public void registra(String chavePix, RegistraWebhookRequest request) throws HttpRestClientException {
        if (request == null) {
            throw new HttpRestClientException("A requisição de registro de webhook não pode ser nula", null, 400);
        }
        LOGGER.info("Efetuando registro de webhook por chave PIX no servidor do Itaú :: {}", request.getWebhookUrl());

        sessionHandler.put(SERVICE_RELATIVE_URL.concat("/" + chavePix), request, RestResponse.class);

        LOGGER.info("Webhook registrado :: {}", request.getWebhookUrl());

    }
}
