package br.com.zup.mercadolivre.shopping;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PaypalReturnDTO implements GatewayPaymentReturnDTO {

    @Min(0)
    @Max(1)
    private int status;

    @NotBlank
    private String transactionId;

    public PaypalReturnDTO(@Min(0) @Max(1) int status, @NotBlank String transactionId) {
        this.status = status;
        this.transactionId = transactionId;
    }

    public Transaction toTransaction(Shopping shopping) {
        StatusTransation status = this.status == 0 ? StatusTransation.error : StatusTransation.success;
        return new Transaction(status, transactionId, shopping);
    }
}
