package br.com.zup.mercadolivre.questions;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.users.User;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private Instant createdAt = Instant.now();

    @ManyToOne
    @NotNull
    @Valid
    private User creator;

    @ManyToOne
    @NotNull
    @Valid
    private Product product;

    @Deprecated
    Question(){}

    public Question(@NotBlank String title,
                    @NotNull @Valid User creator,
                    @NotNull @Valid Product product) {
        this.title = title;
        this.creator = creator;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creator=" + creator +
                ", product=" + product +
                '}';
    }

    public User getCreator() {
        return creator;
    }

    public User getProductOwner() {
        return product.getOwner();
    }
}
