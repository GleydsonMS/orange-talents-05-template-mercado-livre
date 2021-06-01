package br.com.zup.mercadolivre.products;

import br.com.zup.mercadolivre.categories.CategoryRepository;
import br.com.zup.mercadolivre.products.features.FeatureRepository;
import br.com.zup.mercadolivre.users.User;
import br.com.zup.mercadolivre.validators.ForbidFeatureWithSameName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new ForbidFeatureWithSameName());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ProductDTO productDTO, @AuthenticationPrincipal User owner) {
        Product product = productDTO.toModel(categoryRepository, owner);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }
}
