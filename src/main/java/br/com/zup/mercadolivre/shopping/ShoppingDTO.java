package br.com.zup.mercadolivre.shopping;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.users.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ShoppingDTO {

    @Positive
    private Integer quantity;

    @NotNull
    private Long productId;

    @NotNull
    private GatewayPayment gateway;

    public ShoppingDTO(@Positive Integer quantity,
                       @NotNull Long productId,
                       @NotNull GatewayPayment gateway) {
        this.quantity = quantity;
        this.productId = productId;
        this.gateway = gateway;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public GatewayPayment getGateway() {
        return gateway;
    }

    public Shopping toModel(Product product, User customer) {
        return new Shopping(product, this.quantity, customer, this.gateway);
    }
}
