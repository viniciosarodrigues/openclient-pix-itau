package openclient.pix.itau;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openclient.pix.itau.config.ClientConfig;
import openclient.pix.itau.rest.exceptions.HttpRestClientException;
import openclient.pix.itau.service.AuthenticationService;
import openclient.pix.itau.service.RestService;
import openclient.pix.itau.service.v2.cobranca.CobrancaService;
import openclient.pix.itau.service.v2.webhook.WebhookService;

public class PixItauClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(PixItauClient.class);

    private SessionHandler sessionHandler = null;
    private HashMap<Class<? extends RestService>, RestService> instances = new HashMap<Class<? extends RestService>, RestService>();

    /**
     * Inicializa o client Pix Itaú
     * 
     * @param clientConfig Configurações do Client
     * @throws HttpRestClientException
     */
    public PixItauClient(ClientConfig clientConfig) throws HttpRestClientException {
        if (clientConfig == null) {
            throw new NullPointerException("Configuração do Client Pix nula!");
        }
        this.sessionHandler = new SessionHandler(clientConfig);
        this.signin();
        String implementationVersion = getClass().getPackage().getImplementationVersion();
        LOGGER.info("Pix Itau Client versão '{}' inicializado.", implementationVersion != null ? implementationVersion : "dev");
    }

    /**
     * Realiza a autenticação na API
     * 
     * @throws HttpRestClientException
     */
    private void signin() throws HttpRestClientException {
        getInstance(AuthenticationService.class).signin();
    }

    /**
     * Retorna uma instância do serviço de Webhooks
     * 
     * @return WebhookService - Serviços de webhooks
     * @throws HttpRestClientException
     */
    public WebhookService getWebhookService() throws HttpRestClientException {
        return getInstance(WebhookService.class);
    }

    /**
     * Retorna uma instância do serviço de Cobranças
     * 
     * @return CobrancaService - Serviços de cobranças
     * @throws HttpRestClientException
     */
    public CobrancaService getCobrancaService() throws HttpRestClientException {
        return getInstance(CobrancaService.class);
    }

    /**
     * Tratador de instâncias do Client
     * 
     * @param <R> Tipo da classe de serviço
     * @param clazz Classe
     * @return Instância do serviço
     */
    @SuppressWarnings("unchecked")
    private synchronized <R extends RestService> R getInstance(Class<R> clazz) {
        R restService = (R) instances.get(clazz);
        if (restService == null) {
            try {
                Constructor<R> constructor = clazz.getConstructor(SessionHandler.class);
                restService = constructor.newInstance(this.sessionHandler);
                instances.put(clazz, restService);
            } catch (Exception e) {
                throw new RuntimeException("Não foi possível instanciar a classe de serviço REST: " + clazz.getName());
            }
        }
        return restService;
    }

}
