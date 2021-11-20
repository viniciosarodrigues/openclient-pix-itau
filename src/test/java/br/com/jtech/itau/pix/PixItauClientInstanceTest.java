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
import openclient.pix.itau.service.v2.invoice.protocol.AdditionalInformation;
import openclient.pix.itau.service.v2.invoice.protocol.CreateInvoiceRequest;
import openclient.pix.itau.service.v2.invoice.protocol.InvoiceCalendar;
import openclient.pix.itau.service.v2.invoice.protocol.InvoicePerson;
import openclient.pix.itau.service.v2.invoice.protocol.InvoiceValue;
import openclient.pix.itau.service.v2.webhook.protocol.RegisterWebhookRequest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PixItauClientInstanceTest {

    private static final String CLIENTSECRET = "SEU_CLIENT_SECRET_AQUI";
    private static final String CLIENTID = "SEU_CLIENT_ID_AQUI";
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
    public void V00001_checkSuccessConnection() {
        assertNotNull(client);
    }

    @Test
    public void V00002_checkWebhookServiceInstance() throws HttpRestClientException {
        assertNotNull(client.getWebhookService());
    }

    @Test(expected = Test.None.class)
    public void V00003_registerWebhookSuccess() throws HttpRestClientException {
        client.getWebhookService().register(PIX_KEY, new RegisterWebhookRequest(WEBHOOK_URL));
    }

    @Test
    public void V00004_findWebhookSuccess() throws HttpRestClientException {
        assertNotNull(client.getWebhookService().findByPIXKey(PIX_KEY));
    }

    @Test
    public void V00005_checkInvoiceServiceInstance() throws HttpRestClientException {
        assertNotNull(client.getInvoiceService());
    }

    @Test
    public void V00006_createInvoice() throws HttpRestClientException {
        assertNotNull(client.getInvoiceService().create(PixItauClientInstanceTest.buildInvoiceRequest()));
    }

    @Test
    public void V00007_findInvoiceByTxId() throws HttpRestClientException {
        assertNotNull(client.getInvoiceService().findByTxid(TXID_DEFAULT));
    }

    private static CreateInvoiceRequest buildInvoiceRequest() {
        CreateInvoiceRequest request = new CreateInvoiceRequest();
        request.setPixKey(PIX_KEY);
        request.setCalendar(new InvoiceCalendar(3600, null));
        request.setValue(new InvoiceValue(INVOICE_VALUE));
        request.setDebtor(new InvoicePerson(CUSTOMER_NAME, null, CPF));
        request.setAdditionalInformation(Arrays.asList(new AdditionalInformation(INF_ADD_KEY, INF_ADD_VALUE)));
        return request;
    }
}
