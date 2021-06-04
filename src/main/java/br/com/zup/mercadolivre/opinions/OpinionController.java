package br.com.zup.mercadolivre.opinions;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.products.ProductRepository;
import br.com.zup.mercadolivre.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OpinionController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OpinionRepository opinionRepository;

    @PostMapping("/products/{id}/opinions")
    public ResponseEntity<?> create(@RequestBody @Valid OpinionDTO opinionDTO,
                         @PathVariable("id") Long id,
                         @AuthenticationPrincipal User customer) {

        Product product = productRepository.findById(id).get();
        Opinion opinion = opinionDTO.toModel(product, customer);
        opinionRepository.save(opinion);
        return ResponseEntity.ok().build();
    }
}
