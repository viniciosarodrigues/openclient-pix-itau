package openclient.pix.itau.service.v2.webhook.protocol;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuscaWebhookResponse implements Serializable {

    private static final long serialVersionUID = -8824965236847235625L;

    private String webhookUrl;
    @JsonProperty("chave")
    private String chave;
    @JsonProperty("criacao")
    private String criacao;

    public BuscaWebhookResponse() {
        super();
    }

    public BuscaWebhookResponse(String webhookUrl, String chave, String criacao) {
        super();
        this.webhookUrl = webhookUrl;
        this.chave = chave;
        this.criacao = criacao;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getCriacao() {
        return criacao;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BuscaWebhookResponse [webhookUrl=");
        builder.append(webhookUrl);
        builder.append(", chave=");
        builder.append(chave);
        builder.append(", criacao=");
        builder.append(criacao);
        builder.append("]");
        return builder.toString();
    }

}
