package br.com.zup.mercadolivre.products;

import br.com.zup.mercadolivre.categories.CategoryRepository;
import br.com.zup.mercadolivre.products.images.ImagesDTO;
import br.com.zup.mercadolivre.products.images.UploadImages;
import br.com.zup.mercadolivre.users.User;
import br.com.zup.mercadolivre.validators.ForbidFeatureWithSameName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UploadImages uploadImages;

    @InitBinder(value = "ProductDTO")
    public void init(WebDataBinder binder) {
        binder.addValidators(new ForbidFeatureWithSameName());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ProductDTO productDTO, @AuthenticationPrincipal User owner) {
        Product product = productDTO.toModel(categoryRepository, owner);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/images")
    public ResponseEntity<?> createImages(@PathVariable("id") Long productId,
                                          @Valid ImagesDTO imagesDTO,
                                          @AuthenticationPrincipal User owner) {

        Set<String> links = uploadImages.send(imagesDTO.getImages());
        Product product = productRepository.findById(productId).get();

        if (!product.belongsUser(owner)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        product.connectImages(links);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }
}
