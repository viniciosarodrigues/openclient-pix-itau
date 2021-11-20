package openclient.pix.itau.service.v2.webhook.protocol;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FindWebhookByIdResponse implements Serializable {

    private static final long serialVersionUID = -8824965236847235625L;

    private String webhookUrl;
    @JsonProperty("chave")
    private String pixKey;
    @JsonProperty("criacao")
    private String createdAt;

    public FindWebhookByIdResponse() {
        super();
    }

    public FindWebhookByIdResponse(String webhookUrl, String pixKey, String createdAt) {
        super();
        this.webhookUrl = webhookUrl;
        this.pixKey = pixKey;
        this.createdAt = createdAt;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
        builder.append("FindWebhookByIdResponse [webhookUrl=");
        builder.append(webhookUrl);
        builder.append(", pixKey=");
        builder.append(pixKey);
        builder.append(", createdAt=");
        builder.append(createdAt);
        builder.append("]");
        return builder.toString();
    }

}
