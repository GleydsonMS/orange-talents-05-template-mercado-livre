package br.com.zup.mercadolivre.productDetails;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDetailsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductDetailsDTO> view(@PathVariable("id") Long id){
        Product product = productRepository.findById(id).get();
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO(product);
        return ResponseEntity.ok(productDetailsDTO);
    }
}
