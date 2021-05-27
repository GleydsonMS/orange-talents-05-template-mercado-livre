package br.com.zup.mercadolivre.categories;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @ManyToOne
    private Category motherCategory;

    @Deprecated
    private Category() {}

    public Category(@NotBlank  String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getMotherCategory() {
        return motherCategory;
    }

    public void setMotherCategory(Category motherCategory) {
        this.motherCategory = motherCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", motherCategory=" + motherCategory +
                '}';
    }
}
