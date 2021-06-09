package br.com.zup.mercadolivre.shopping;

import br.com.zup.mercadolivre.products.Product;
import br.com.zup.mercadolivre.products.ProductRepository;
import br.com.zup.mercadolivre.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/shopping")
public class ShoppingClosingInitController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private EmailShopping emails;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ShoppingDTO shoppingDTO,
                                    @AuthenticationPrincipal User customer,
                                    UriComponentsBuilder uriComponentsBuilder) throws BindException {

        Product product = productRepository.findById(shoppingDTO.getProductId()).get();
        int quantity = shoppingDTO.getQuantity();
        boolean check = product.destock(quantity);

        if (check) {
            Shopping shopping = shoppingDTO.toModel(product, customer);
            shoppingRepository.save(shopping);
            emails.newShopping(shopping);
            String urlReturn = shopping.getGateway().returnPayment(uriComponentsBuilder, shopping);
            return ResponseEntity.status(HttpStatus.FOUND).body(urlReturn);
        }

        BindException destockProblem = new BindException(shoppingDTO, "ShoppingDTO");
        destockProblem.reject(null, "Não há estoque suficiente para a realização da compra.");
        throw destockProblem;
    }
}
