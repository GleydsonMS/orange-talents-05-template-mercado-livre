package br.com.zup.mercadolivre.shopping;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private StatusTransation status;

    @NotBlank
    private String transactionGatewayId;

    @ManyToOne
    @NotNull
    @Valid
    private Shopping shopping;

    @NotNull
    private Instant instant;

    @Deprecated
    public Transaction() {}

    public Transaction(@NotNull StatusTransation status,
                       @NotBlank String transactionGatewayId,
                       @NotNull @Valid Shopping shopping) {
        this.status = status;
        this.transactionGatewayId = transactionGatewayId;
        this.shopping = shopping;
        this.instant = Instant.now();
    }

    public boolean successfullyFinished() {
        return this.status.equals(StatusTransation.success);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (status != that.status) return false;
        if (!transactionGatewayId.equals(that.transactionGatewayId)) return false;
        return shopping.equals(that.shopping);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + transactionGatewayId.hashCode();
        result = 31 * result + shopping.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", status=" + status +
                ", transactionGatewayId='" + transactionGatewayId + '\'' +
                ", shopping=" + shopping +
                ", instant=" + instant +
                '}';
    }
}
