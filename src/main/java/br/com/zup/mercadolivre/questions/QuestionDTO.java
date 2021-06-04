package br.com.zup.mercadolivre.questions;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.users.User;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QuestionDTO {

    @NotBlank
    private String title;

    @Deprecated
    QuestionDTO(){}

    public QuestionDTO(@NotBlank String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public Question toModel(@NotNull @Valid User creator, @NotNull @Valid Product product) {
        return new Question(title, creator, product);
    }
}
