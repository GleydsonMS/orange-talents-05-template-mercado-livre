package br.com.zup.mercadolivre.categories;

import br.com.zup.mercadolivre.validators.ExistId;
import br.com.zup.mercadolivre.validators.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CategoryDTO {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;

    @Positive
    @ExistId(domainClass = Category.class, fieldName = "id")
    private Long motherCategoryId;

    @Deprecated
    private CategoryDTO() {}

    public CategoryDTO(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getMotherCategoryId() {
        return motherCategoryId;
    }

    public void setMotherCategoryId(Long motherCategoryId) {
        this.motherCategoryId = motherCategoryId;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "name='" + name + '\'' +
                ", motherCategoryId=" + motherCategoryId +
                '}';
    }

    public Category toModel(CategoryRepository categoryRepository) {
        if (this.motherCategoryId != null) {
            Optional<Category> momCategory = categoryRepository.findById(motherCategoryId);
            Category category =  new Category(name);
            category.setMotherCategory(momCategory.get());
            return category;
        }

        return new Category(name);
    }
}
