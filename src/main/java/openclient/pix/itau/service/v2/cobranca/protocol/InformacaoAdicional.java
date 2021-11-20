package openclient.pix.itau.service.v2.cobranca.protocol;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa informação adicional
 * 
 * @author viniciosarodrigues
 *
 */
public class InformacaoAdicional implements Serializable {

    private static final long serialVersionUID = 1237414527267732475L;

    @JsonProperty("nome")
    private String nome;
    @JsonProperty("valor")
    private String valor;

    public InformacaoAdicional() {
        super();
    }

    public InformacaoAdicional(String nome, String valor) {
        super();
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InformacaoAdicional [nome=");
        builder.append(nome);
        builder.append(", valor=");
        builder.append(valor);
        builder.append("]");
        return builder.toString();
    }

}
