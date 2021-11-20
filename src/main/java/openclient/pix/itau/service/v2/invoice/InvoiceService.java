package openclient.pix.itau.service.v2.invoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import openclient.pix.itau.SessionHandler;
import openclient.pix.itau.rest.exceptions.HttpRestClientException;
import openclient.pix.itau.service.RestService;
import openclient.pix.itau.service.v2.invoice.protocol.CreateInvoiceRequest;

/**
 * Camada de consumo de serviços para Cobrança
 * 
 * @author viniciosarodrigues
 *
 */
public class InvoiceService extends RestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);
    private static final String SERVICE_RELATIVE_URL = "/pix_recebimentos/v2/cob";

    public InvoiceService(SessionHandler sessionHandler) {
        super(sessionHandler);
    }

    /**
     * Cria uma ordem de cobrança de fatura na API do PIX Itaú
     * 
     * @param request Requisição de criação de cobrança na API do PIX Itaú
     * @return Cobrança criada
     * @throws HttpRestClientException
     */
    public CreateInvoiceResponse create(CreateInvoiceRequest request) throws HttpRestClientException {
        if (request == null) {
            throw new HttpRestClientException("A requisição não pode ser nula na criação da cobrança", null, 400);
        }

        LOGGER.info("Efetuando processo de criação de cobrança na API de PIX Itaú :: Chave {} | Valor R$ {} | Inf. Adicionais {}",
                    request.getPixKey(), request.getValue().getOriginal(), request.getAdditionalInformation());

        CreateInvoiceResponse response = sessionHandler.post(SERVICE_RELATIVE_URL, request, CreateInvoiceResponse.class);

        LOGGER.info("Cobrança criada com sucesso :: txid -> {}", response.getTxid());

        return response;
    }

    /**
     * Busca informõaçes de uma cobrança na API do PIX Itaú pelo txid
     * 
     * @param txid Identificador da cobrança na API PIX
     * @return Informações detalhadas de uma cobrança
     * @throws HttpRestClientException Posísvel erro na request
     */
    public CreateInvoiceResponse findByTxid(String txid) throws HttpRestClientException {
        if (txid == null || txid.trim().isEmpty()) {
            throw new HttpRestClientException("TXID não pode ser nula na consulta da cobrança", null, 400);
        }

        LOGGER.info("Efetuando processo de consulta de cobrança na API de PIX Itaú :: Chave {}", txid);

        CreateInvoiceResponse response = sessionHandler.get(SERVICE_RELATIVE_URL + "/" + txid, null, CreateInvoiceResponse.class);

        LOGGER.info("Cobrança encontrada com sucesso :: txid -> {} | valor -> {}", response.getTxid(), response.getValue().getOriginal());

        return response;
    }

}
