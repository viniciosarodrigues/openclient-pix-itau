package openclient.pix.itau.service.v2.cobranca;

/**
 * Status da Cobrança
 * 
 * @author viniciosarodrigues
 *
 */
public enum CobrancaStatus {
    ATIVA,
    CONCLUIDA,
    REMOVIDA_PELO_PSP,
    REMOVIDA_PELO_USUARIO_RECEBEDOR
}
