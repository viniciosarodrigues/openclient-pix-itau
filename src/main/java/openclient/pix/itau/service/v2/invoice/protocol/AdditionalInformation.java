package openclient.pix.itau.service.v2.invoice.protocol;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa informação adicional
 * 
 * @author viniciosarodrigues
 *
 */
public class AdditionalInformation implements Serializable {

    private static final long serialVersionUID = 1237414527267732475L;

    @JsonProperty("nome")
    private String name;
    @JsonProperty("valor")
    private String value;

    public AdditionalInformation() {
        super();
    }

    public AdditionalInformation(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AdditionalInformation [name=");
        builder.append(name);
        builder.append(", value=");
        builder.append(value);
        builder.append("]");
        return builder.toString();
    }

}
