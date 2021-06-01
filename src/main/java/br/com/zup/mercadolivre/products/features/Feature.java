package br.com.zup.mercadolivre.products.features;

import br.com.zup.mercadolivre.products.Product;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @ManyToOne
    @NotNull @Valid
    private Product product;

    @Deprecated
    private Feature() {}

    public Feature(@NotBlank String name,
                   @NotBlank String description,
                   @NotNull @Valid Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feature)) return false;

        Feature feature = (Feature) o;

        if (!getName().equals(feature.getName())) return false;
        return product.equals(feature.product);
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }
}
