package br.com.zup.mercadolivre.shopping;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.users.User;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Product product;

    @Positive
    private int quantity;

    @ManyToOne
    @NotNull
    @Valid
    private User customer;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GatewayPayment gateway;

    @NotNull
    private BigDecimal currentPrice;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ShoppingStatus status;


    public Shopping(@NotNull @Valid Product product,
                    @Positive int quantity,
                    @NotNull @Valid User customer,
                    @NotNull GatewayPayment gateway) {
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
        this.gateway = gateway;
        this.currentPrice = product.getPrice();
        this.status = ShoppingStatus.INICIADA;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public User getCustomer() {
        return customer;
    }

    public GatewayPayment getGateway() {
        return gateway;
    }

    public User getOwner() {
        return product.getOwner();
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public ShoppingStatus getStatus() {
        return status;
    }
}
