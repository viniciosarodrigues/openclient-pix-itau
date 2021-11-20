package openclient.pix.itau.service.v2.cobranca.protocol;

import java.io.Serializable;

/**
 * Representa informações de valores da cobrança
 * 
 * @author viniciosarodrigues
 *
 */
public class Valor implements Serializable {

    private static final long serialVersionUID = 7068058093924841657L;

    private String original;

    public Valor() {
        super();
    }

    public Valor(String original) {
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
        builder.append("Valor [original=");
        builder.append(original);
        builder.append("]");
        return builder.toString();
    }

}
