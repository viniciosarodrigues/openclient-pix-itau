package openclient.pix.itau.service.v2.webhook.protocol;

import java.io.Serializable;

/**
 * Requisição de registro de chave e URL de API no Webhook do Itaú
 * 
 * @author viniciosarodrigues
 *
 */
public class RegisterWebhookRequest implements Serializable {

    private static final long serialVersionUID = -4866411464971470840L;

    private String webhookUrl;

    public RegisterWebhookRequest() {
        super();
    }

    public RegisterWebhookRequest(String webhookUrl) {
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
        builder.append("RegisterWebhookRequest [webhookUrl=");
        builder.append(webhookUrl);
        builder.append("]");
        return builder.toString();
    }

}
