package br.com.zup.mercadolivre.opinions;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.users.User;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class OpinionDTO {

    @Min(1)
    @Max(5)
    private Integer score;

    @NotBlank
    private String title;

    @NotBlank
    @Size(max = 500)
    private String description;

    @Deprecated
    OpinionDTO(){}

    public OpinionDTO(@Size(min = 1, max = 5) Integer score,
                      @NotBlank String title,
                      @NotBlank @Size(max = 500) String description) {
        this.score = score;
        this.title = title;
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "OpinionDTO{" +
                "score=" + score +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Opinion toModel(@NotNull @Valid Product product, @NotNull @Valid User customer) {
        return new Opinion(score, title, description, product, customer);
    }
}
