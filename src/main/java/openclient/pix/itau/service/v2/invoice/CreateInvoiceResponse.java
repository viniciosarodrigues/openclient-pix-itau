package openclient.pix.itau.service.v2.invoice;

import com.fasterxml.jackson.annotation.JsonProperty;

import openclient.pix.itau.service.v2.invoice.protocol.CreateInvoiceRequest;

/**
 * Representa a resposta da criação da cobrança
 * 
 * @author viniciosarodrigues
 *
 */
public class CreateInvoiceResponse extends CreateInvoiceRequest {

    private static final long serialVersionUID = 5363679159514769616L;

    private InvoiceStatus status;

    private String txid;

    @JsonProperty("revisao")
    private int revision;

    private String location;

    public CreateInvoiceResponse() {
        super();
    }

    public CreateInvoiceResponse(InvoiceStatus status, String txid, int revision, String location) {
        super();
        this.status = status;
        this.txid = txid;
        this.revision = revision;
        this.location = location;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CreateInvoiceResponse [status=");
        builder.append(status);
        builder.append(", txid=");
        builder.append(txid);
        builder.append(", revision=");
        builder.append(revision);
        builder.append(", location=");
        builder.append(location);
        builder.append("]");
        return builder.toString();
    }

}
