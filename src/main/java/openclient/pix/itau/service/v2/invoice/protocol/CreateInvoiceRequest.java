package openclient.pix.itau.service.v2.invoice.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe de criação de Cobrança PIX Itaú
 * 
 * @author viniciosarodrigues
 *
 */
public class CreateInvoiceRequest implements Serializable {

    private static final long serialVersionUID = -2491379393232347126L;

    @JsonProperty("calendario")
    private InvoiceCalendar calendar;

    @JsonProperty("devedor")
    private InvoicePerson debtor;

    @JsonProperty("valor")
    private InvoiceValue value;

    @JsonProperty("chave")
    private String pixKey;

    @JsonProperty("infoAdicionais")
    private List<AdditionalInformation> additionalInformation = new ArrayList<>();

    public InvoiceCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(InvoiceCalendar calendar) {
        this.calendar = calendar;
    }

    public InvoicePerson getDebtor() {
        return debtor;
    }

    public void setDebtor(InvoicePerson debtor) {
        this.debtor = debtor;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public List<AdditionalInformation> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<AdditionalInformation> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public InvoiceValue getValue() {
        return value;
    }

    public void setValue(InvoiceValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CreateInvoiceRequest [calendar=");
        builder.append(calendar);
        builder.append(", debtor=");
        builder.append(debtor);
        builder.append(", value=");
        builder.append(value);
        builder.append(", pixKey=");
        builder.append(pixKey);
        builder.append(", additionalInformation=");
        builder.append(additionalInformation);
        builder.append("]");
        return builder.toString();
    }

}
