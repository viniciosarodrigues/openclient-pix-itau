package openclient.pix.itau.service.v2.cobranca.protocol;

import openclient.pix.itau.service.v2.cobranca.CobrancaStatus;

/**
 * Representa a resposta da criação da cobrança
 * 
 * @author viniciosarodrigues
 *
 */
public class CriaCobrancaResponse extends CriaCobrancaRequest {

    private static final long serialVersionUID = 5363679159514769616L;

    private CobrancaStatus status;

    private String txid;

    private int revisao;

    private String location;

    public CriaCobrancaResponse() {
        super();
    }

    public CriaCobrancaResponse(CobrancaStatus status, String txid, int revisao, String location) {
        super();
        this.status = status;
        this.txid = txid;
        this.revisao = revisao;
        this.location = location;
    }

    public CobrancaStatus getStatus() {
        return status;
    }

    public void setStatus(CobrancaStatus status) {
        this.status = status;
    }

    public int getRevisao() {
        return revisao;
    }

    public void setRevisao(int revisao) {
        this.revisao = revisao;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CriaCobrancaResponse [status=");
        builder.append(status);
        builder.append(", txid=");
        builder.append(txid);
        builder.append(", revisao=");
        builder.append(revisao);
        builder.append(", location=");
        builder.append(location);
        builder.append("]");
        return builder.toString();
    }

}
