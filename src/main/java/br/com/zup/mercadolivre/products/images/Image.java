package br.com.zup.mercadolivre.products.images;

import br.com.zup.mercadolivre.products.Product;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Product product;

    @URL
    @NotBlank
    private String link;

    @Deprecated
    private Image() {}

    public Image(@NotNull @Valid Product product,
                 @URL @NotBlank String link) {
        this.product = product;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;

        Image image = (Image) o;

        if (!product.equals(image.product)) return false;
        return link.equals(image.link);
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + link.hashCode();
        return result;
    }
}
