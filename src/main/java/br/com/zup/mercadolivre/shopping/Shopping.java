package br.com.zup.mercadolivre.shopping;

import antlr.collections.List;
import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.users.User;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "shopping", cascade = CascadeType.MERGE)
    private Set<Transaction> transactions = new HashSet<>();

    @Deprecated
    public Shopping() {}


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

    public void addTransaction(@Valid GatewayPaymentReturnDTO gatewayPaymentReturnDTO) {
        Transaction newTransaction =  gatewayPaymentReturnDTO.toTransaction(this);

        Assert.isTrue(transactionsSuccessfulyFinished().isEmpty(), "A compra já foi concluída com sucesso.");

        this.transactions.add(newTransaction);
    }

    private Set<Transaction> transactionsSuccessfulyFinished() {
        Set<Transaction> transactionsSuccessfullyFinished = this.transactions.stream()
                .filter(Transaction::successfullyFinished).collect(Collectors.toSet());
        return transactionsSuccessfullyFinished;
    }

    public boolean successfullyProcessed() {
        return !transactionsSuccessfulyFinished().isEmpty();
    }
}
