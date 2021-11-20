package openclient.pix.itau.service.v2.cobranca.protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de criação de Cobrança PIX Itaú
 * 
 * @author viniciosarodrigues
 *
 */
public class CriaCobrancaRequest implements Serializable {

    private static final long serialVersionUID = -2491379393232347126L;

    private Calendario calendario;

    private Pessoa devedor;

    private Valor valor;

    private String chave;

    private List<InformacaoAdicional> infoAdicionais = new ArrayList<>();

    public CriaCobrancaRequest() {
        super();
    }

    public CriaCobrancaRequest(Calendario calendario, Pessoa devedor, Valor valor, String chave, List<InformacaoAdicional> infoAdicionais) {
        super();
        this.calendario = calendario;
        this.devedor = devedor;
        this.valor = valor;
        this.chave = chave;
        this.infoAdicionais = infoAdicionais;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public Pessoa getDevedor() {
        return devedor;
    }

    public void setDevedor(Pessoa devedor) {
        this.devedor = devedor;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public List<InformacaoAdicional> getInfoAdicionais() {
        return infoAdicionais;
    }

    public void setInfoAdicionais(List<InformacaoAdicional> infoAdicionais) {
        this.infoAdicionais = infoAdicionais;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CriaCobrancaRequest [calendario=");
        builder.append(calendario);
        builder.append(", devedor=");
        builder.append(devedor);
        builder.append(", valor=");
        builder.append(valor);
        builder.append(", chave=");
        builder.append(chave);
        builder.append(", infoAdicionais=");
        builder.append(infoAdicionais);
        builder.append("]");
        return builder.toString();
    }

}
