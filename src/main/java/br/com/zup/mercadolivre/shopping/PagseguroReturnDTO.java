package br.com.zup.mercadolivre.shopping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagseguroReturnDTO implements GatewayPaymentReturnDTO {

    @NotBlank
    private String transactionId;

    @NotNull
    private ReturnPagseguroStatus status;

    public PagseguroReturnDTO(@NotBlank String transactionId,
                              @NotNull ReturnPagseguroStatus status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "PagseguroReturnDTO{" +
                "transactionId='" + transactionId + '\'' +
                ", status=" + status +
                '}';
    }

    public Transaction toTransaction(Shopping shopping) {
        return new Transaction(status.normalize(), transactionId, shopping);
    }
}
