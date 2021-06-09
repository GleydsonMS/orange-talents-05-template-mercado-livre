package br.com.zup.mercadolivre.externalSystems;

public class NotaFiscalDTO {

    private Long shoppingId;

    private Long customerId;

    public NotaFiscalDTO(Long shoppingId, Long customerId) {
        this.shoppingId = shoppingId;
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getShoppingId() {
        return shoppingId;
    }

    @Override
    public String toString() {
        return "NotasFiscalDTO{" +
                "shoppingId=" + shoppingId +
                ", customerId=" + customerId +
                '}';
    }
}
