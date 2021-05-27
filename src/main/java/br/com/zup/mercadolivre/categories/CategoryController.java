package br.com.zup.mercadolivre.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CategoryDTO categoryDTO) {
        Category category = categoryDTO.toModel(categoryRepository);
        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }
}
