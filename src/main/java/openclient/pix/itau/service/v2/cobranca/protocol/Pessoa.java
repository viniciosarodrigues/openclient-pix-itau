package openclient.pix.itau.service.v2.cobranca.protocol;

import java.io.Serializable;

/**
 * Representa uma pessoa física ou jurídica da cobrança PIX
 * 
 * @author viniciosarodrigues
 *
 */
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 5630521026904423020L;

    private String nome;

    private String cnpj;

    private String cpf;

    public Pessoa() {
        super();
    }

    public Pessoa(String nome, String cnpj, String cpf) {
        super();
        this.nome = nome;
        this.cnpj = cnpj;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        builder.append("Pessoa [nome=");
        builder.append(nome);
        builder.append(", cnpj=");
        builder.append(cnpj);
        builder.append(", cpf=");
        builder.append(cpf);
        builder.append("]");
        return builder.toString();
    }

}
