package br.com.zup.mercadolivre.products;

import br.com.zup.mercadolivre.categories.Category;
import br.com.zup.mercadolivre.categories.CategoryRepository;
import br.com.zup.mercadolivre.products.features.FeatureDTO;
import br.com.zup.mercadolivre.users.User;
import br.com.zup.mercadolivre.validators.ExistId;
import br.com.zup.mercadolivre.validators.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDTO {

    @NotBlank
    @UniqueValue(domainClass = Product.class, fieldName = "name")
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    private Long quantity;

    @Size(min = 3)
    @Valid
    private List<FeatureDTO> features;

    @NotBlank
    @Length(max = 1000)
    private String description;

    @NotNull
    @ExistId(domainClass = Category.class, fieldName = "id")
    private Long categoryId;

    @Deprecated
    private ProductDTO() {}

    public ProductDTO(@NotBlank String name,
                      @NotNull @Positive BigDecimal price,
                      @NotNull @PositiveOrZero Long quantity,
                      @Size(min = 3) List<FeatureDTO> features,
                      @NotBlank @Length(max = 1000) String description,
                      @NotNull Long categoryId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.features.addAll(features);
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public List<FeatureDTO> getFeatures() {
        return features;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Product toModel(CategoryRepository categoryRepository, User owner) {
        Category category = categoryRepository.findById(categoryId).get();
        return new Product(name, price, quantity, features, description, category, owner);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", features=" + features +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }

    public Set<String> findSameFeatures() {
        HashSet<String> sameNames = new HashSet<>();
        HashSet<String> results = new HashSet<>();
        for (FeatureDTO feature : features) {
            String name = feature.getName();
            if(!sameNames.add(name)) {
                results.add(name);
            }
        }
        return results;
    }
}
