package br.com.zup.mercadolivre.shopping;

public interface GatewayPaymentReturnDTO {

    Transaction toTransaction(Shopping shopping);
}
