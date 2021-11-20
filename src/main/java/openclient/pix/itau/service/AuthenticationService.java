package openclient.pix.itau.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openclient.pix.itau.SessionHandler;
import openclient.pix.itau.rest.exceptions.HttpRestClientException;

/**
 * Serviço de autenticação à API do Itaú
 * 
 * @author viniciosarodrigues
 *
 */
public class AuthenticationService extends RestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(SessionHandler sessionHandler) {
        super(sessionHandler);
    }

    public void signin() throws HttpRestClientException {
        LOGGER.info("Efetuando signin no servidor...");
        sessionHandler.signin();
    }
}
