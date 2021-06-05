package br.com.zup.mercadolivre.productDetails;

import br.com.zup.mercadolivre.products.features.Feature;

public class ProductFeatureDetails {

    private final String name;
    private final String description;

    public ProductFeatureDetails(Feature feature) {
        this.name = feature.getName();
        this.description = feature.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
