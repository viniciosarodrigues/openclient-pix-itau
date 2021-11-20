package openclient.pix.itau.service;

import openclient.pix.itau.SessionHandler;

public class RestService {
    protected SessionHandler sessionHandler;

    public RestService(SessionHandler sessionHandler) {
        if (sessionHandler == null) {
            throw new NullPointerException("Handler de sessão do client está nulo!!");
        }
        this.sessionHandler = sessionHandler;
    }

}
