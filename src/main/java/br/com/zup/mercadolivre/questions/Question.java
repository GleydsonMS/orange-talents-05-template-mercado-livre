package br.com.zup.mercadolivre.questions;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.users.User;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Question implements Comparable<Question> {

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

    public String getTitle() {
        return title;
    }

    public User getCreator() {
        return creator;
    }

    public User getProductOwner() {
        return product.getOwner();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (!getTitle().equals(question.getTitle())) return false;
        if (!getCreator().equals(question.getCreator())) return false;
        return product.equals(question.product);
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getCreator().hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }

    @Override
    public int compareTo(@org.jetbrains.annotations.NotNull Question o) {
        return this.title.compareTo(o.title);
    }
}
