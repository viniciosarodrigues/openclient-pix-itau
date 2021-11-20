package openclient.pix.itau.service.v2.invoice.protocol;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa uma pessoa física ou jurídica da cobrança PIX
 * 
 * @author viniciosarodrigues
 *
 */
public class InvoicePerson implements Serializable {

    private static final long serialVersionUID = 5630521026904423020L;

    @JsonProperty("nome")
    private String name;

    private String cnpj;

    private String cpf;

    public InvoicePerson() {
        super();
    }

    public InvoicePerson(String name, String cnpj, String cpf) {
        super();
        this.name = name;
        this.cnpj = cnpj;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InvoicePerson [name=");
        builder.append(name);
        builder.append(", cnpj=");
        builder.append(cnpj);
        builder.append(", cpf=");
        builder.append(cpf);
        builder.append("]");
        return builder.toString();
    }

}
