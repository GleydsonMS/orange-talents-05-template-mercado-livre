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
}
