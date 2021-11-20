package openclient.pix.itau.service.v2.invoice.protocol;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe de calendário das requisições e respostas de cobranças PIX
 * 
 * @author viniciosarodrigues
 *
 */
public class InvoiceCalendar implements Serializable {

    private static final long serialVersionUID = 5777790007930914819L;

    @JsonProperty("expiracao")
    private long expiration;

    @JsonProperty("criacao")
    private String createdAt;

    public InvoiceCalendar() {
        super();
    }

    public InvoiceCalendar(long expiration, String createdAt) {
        super();
        this.expiration = expiration;
        this.createdAt = createdAt;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InvoiceCalendar [expiration=");
        builder.append(expiration);
        builder.append(", createdAt=");
        builder.append(createdAt);
        builder.append("]");
        return builder.toString();
    }

}
