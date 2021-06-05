package br.com.zup.mercadolivre.opinions;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.users.User;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    private Integer score;

    @NotBlank
    private String title;

    @NotBlank
    @Size(max = 500)
    private String description;

    @ManyToOne
    @NotNull
    @Valid
    private Product product;

    @ManyToOne
    @NotNull
    @Valid
    private  User customer;

    @Deprecated
    public Opinion(){}

    public Opinion(@Min(1) @Max(5) Integer score,
                   @NotBlank String title,
                   @NotBlank @Size(max = 500) String description,
                   @NotNull @Valid Product product,
                   @NotNull @Valid User customer) {
        this.score = score;
        this.title = title;
        this.description = description;
        this.product = product;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", score=" + score +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", product=" + product +
                ", customer=" + customer +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Opinion)) return false;

        Opinion opinion = (Opinion) o;

        if (!title.equals(opinion.title)) return false;
        if (!description.equals(opinion.description)) return false;
        if (!product.equals(opinion.product)) return false;
        return customer.equals(opinion.customer);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + product.hashCode();
        result = 31 * result + customer.hashCode();
        return result;
    }
}
