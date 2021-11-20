package openclient.pix.itau.service.v2.invoice;

/**
 * Status da Cobrança
 * 
 * @author viniciosarodrigues
 *
 */
public enum InvoiceStatus {
    ATIVA,
    CONCLUIDA,
    REMOVIDA_PELO_PSP,
    REMOVIDA_PELO_USUARIO_RECEBEDOR
}
