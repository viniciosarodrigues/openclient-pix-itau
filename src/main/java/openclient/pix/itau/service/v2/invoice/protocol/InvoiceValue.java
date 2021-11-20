package openclient.pix.itau.service.v2.invoice.protocol;

import java.io.Serializable;

/**
 * Representa informações de valores da cobrança
 * 
 * @author viniciosarodrigues
 *
 */
public class InvoiceValue implements Serializable {

    private static final long serialVersionUID = 7068058093924841657L;

    private String original;

    public InvoiceValue() {
        super();
    }

    public InvoiceValue(String original) {
        super();
        this.original = original;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InvoiceValue [original=");
        builder.append(original);
        builder.append("]");
        return builder.toString();
    }

}
