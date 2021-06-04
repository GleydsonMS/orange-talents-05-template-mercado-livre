package br.com.zup.mercadolivre.products;

import br.com.zup.mercadolivre.categories.Category;
import br.com.zup.mercadolivre.products.features.Feature;
import br.com.zup.mercadolivre.products.features.FeatureDTO;
import br.com.zup.mercadolivre.products.images.Image;
import br.com.zup.mercadolivre.users.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    private Long quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @Size(min = 3)
    private Set<Feature> features = new HashSet<>();

    @NotBlank
    @Column(columnDefinition = "TEXT")
    @Length(max = 1000)
    private String description;

    @ManyToOne
    @NotNull
    @Valid
    private Category category;

    private Instant createdAt = Instant.now();

    @ManyToOne
    @NotNull
    private User owner;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<Image> images = new HashSet<>();

    @Deprecated
    private Product() {}

    public Product(@NotBlank String name,
                   @NotNull @Positive BigDecimal price,
                   @NotNull @PositiveOrZero Long quantity,
                   @Size(min = 3) Collection<FeatureDTO> features,
                   @NotBlank @Length(max = 1000) String description,
                   @NotNull @Valid Category category,
                   @NotNull @Valid User owner) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.features.addAll(features
                .stream().map(feature -> feature.toModel(this))
                .collect(Collectors.toSet()));
    }

    public Long getId() {
        return id;
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

    public Set<Feature> getFeatures() {
        return features;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    public void connectImages(Set<String> links) {
        Set<Image> images = links.stream()
                .map(link -> new Image(this, link))
                .collect(Collectors.toSet());
        this.images.addAll(images);
    }

    public boolean belongsUser(User possibleOwner) {
        return this.owner.equals(possibleOwner);
    }

    public User getOwner() {
        return this.owner;
    }
}
