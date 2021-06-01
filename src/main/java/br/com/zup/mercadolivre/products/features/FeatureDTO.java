package br.com.zup.mercadolivre.products.features;

import br.com.zup.mercadolivre.products.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FeatureDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Deprecated
    private FeatureDTO() {}

    public FeatureDTO(@NotBlank String name,
                      @NotBlank String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Feature toModel(@NotNull @Valid Product product) {
        return new Feature(name, description, product);
    }

    @Override
    public String toString() {
        return "FeatureDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
