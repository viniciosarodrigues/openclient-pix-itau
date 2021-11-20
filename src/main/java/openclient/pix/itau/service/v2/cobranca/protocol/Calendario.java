package openclient.pix.itau.service.v2.cobranca.protocol;

import java.io.Serializable;

/**
 * Classe de calendário das requisições e respostas de cobranças PIX
 * 
 * @author viniciosarodrigues
 *
 */
public class Calendario implements Serializable {

    private static final long serialVersionUID = 5777790007930914819L;

    private long expiracao;

    private String criacao;

    public Calendario() {
        super();
    }

    public Calendario(long expiracao, String criacao) {
        super();
        this.expiracao = expiracao;
        this.criacao = criacao;
    }

    public long getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(long expiracao) {
        this.expiracao = expiracao;
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
        builder.append("Calendario [expiracao=");
        builder.append(expiracao);
        builder.append(", criacao=");
        builder.append(criacao);
        builder.append("]");
        return builder.toString();
    }

}
