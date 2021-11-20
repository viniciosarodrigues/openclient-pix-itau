package br.com.jtech.itau.pix;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import openclient.pix.itau.PixItauClient;
import openclient.pix.itau.config.ClientConfig;
import openclient.pix.itau.config.ClientConfigBuilder;
import openclient.pix.itau.rest.exceptions.HttpRestClientException;
import openclient.pix.itau.service.v2.cobranca.protocol.Calendario;
import openclient.pix.itau.service.v2.cobranca.protocol.CriaCobrancaRequest;
import openclient.pix.itau.service.v2.cobranca.protocol.InformacaoAdicional;
import openclient.pix.itau.service.v2.cobranca.protocol.Pessoa;
import openclient.pix.itau.service.v2.cobranca.protocol.Valor;
import openclient.pix.itau.service.v2.webhook.protocol.RegistraWebhookRequest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PixItauClientInstanceTest {

    private static final String CLIENTSECRET = "";
    private static final String CLIENTID = "";
    protected static PixItauClient client;
    protected static ClientConfig config;

    private static final String TXID_DEFAULT = "7978c0c97ea847e78e8849634473c1f1";
    private static final String INF_ADD_VALUE = "875185";
    private static final String INF_ADD_KEY = "ID_FATURA";
    private static final String CPF = "11122233396";
    private static final String CUSTOMER_NAME = "Vinícios Rodrigues";
    private static final String INVOICE_VALUE = "129.95";
    protected final static String BASE_URL_SANDBOX = "https://api.itau.com.br/sandbox";
    protected final static String WEBHOOK_URL = "https://sistemas.jtech.com.br/sansysOmnichannel/api/rest/v1/pix/webhook";
    protected final static String PIX_KEY = "07aaaf2b-2345-48e3-ac46-45c2db36d228";

    static {
        try {
            config = ClientConfigBuilder.builder()
                    .setBaseUrl(new URL(BASE_URL_SANDBOX))
                    .setClientId(CLIENTID)
                    .setClientSecret(CLIENTSECRET)
                    .setTimeout(15000)
                    .setIgnoreSSLErrors(true).build();

            client = new PixItauClient(config);
        } catch (HttpRestClientException | MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void V00001_checaConexao() {
        assertNotNull(client);
    }

    @Test
    public void V00002_checaWebhookService() throws HttpRestClientException {
        assertNotNull(client.getWebhookService());
    }

    @Test(expected = Test.None.class)
    public void V00003_registraWebhookComSucesso() throws HttpRestClientException {
        client.getWebhookService().registra(PIX_KEY, new RegistraWebhookRequest(WEBHOOK_URL));
    }

    @Test
    public void V00004_buscaWebhookComSucesso() throws HttpRestClientException {
        assertNotNull(client.getWebhookService().buscaPorChavePix(PIX_KEY));
    }

    @Test
    public void V00005_checaCobrancaService() throws HttpRestClientException {
        assertNotNull(client.getCobrancaService());
    }

    @Test
    public void V00006_criaCobrancaComSucesso() throws HttpRestClientException {
        assertNotNull(client.getCobrancaService().cria(PixItauClientInstanceTest.criaRequisicaoDeCriacaoDeCobranca()));
    }

    @Test
    public void V00007_buscaCobrancaPorTXID() throws HttpRestClientException {
        assertNotNull(client.getCobrancaService().buscaCobrancaPorTXID(TXID_DEFAULT));
    }

    private static CriaCobrancaRequest criaRequisicaoDeCriacaoDeCobranca() {
        CriaCobrancaRequest request = new CriaCobrancaRequest();
        request.setChave(PIX_KEY);
        request.setCalendario(new Calendario(3600, null));
        request.setValor(new Valor(INVOICE_VALUE));
        request.setDevedor(new Pessoa(CUSTOMER_NAME, null, CPF));
        request.setInfoAdicionais(Arrays.asList(new InformacaoAdicional(INF_ADD_KEY, INF_ADD_VALUE)));
        return request;
    }
}
