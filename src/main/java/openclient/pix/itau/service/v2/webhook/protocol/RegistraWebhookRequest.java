package openclient.pix.itau.service.v2.webhook.protocol;

import java.io.Serializable;

/**
 * Requisição de registro de chave e URL de API no Webhook do Itaú
 * 
 * @author viniciosarodrigues
 *
 */
public class RegistraWebhookRequest implements Serializable {

    private static final long serialVersionUID = -4866411464971470840L;

    private String webhookUrl;

    public RegistraWebhookRequest() {
        super();
    }

    public RegistraWebhookRequest(String webhookUrl) {
        super();
        this.webhookUrl = webhookUrl;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RegistraWebhookRequest [webhookUrl=");
        builder.append(webhookUrl);
        builder.append("]");
        return builder.toString();
    }

}
