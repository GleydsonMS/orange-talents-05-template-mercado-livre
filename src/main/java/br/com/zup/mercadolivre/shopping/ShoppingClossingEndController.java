package br.com.zup.mercadolivre.shopping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class ShoppingClossingEndController {

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private Set<ShoppingSuccessEvent> events;

    @Autowired
    private EmailFail emailFail;

    @PostMapping(value = "/pagseguro-return/{id}")
    public ResponseEntity<?> processPagseguro(@Valid PagseguroReturnDTO pagseguroReturnDTO,
                                              @PathVariable("id") Long shoppingId,
                                              UriComponentsBuilder uriComponentsBuilder) {
        return process(shoppingId, pagseguroReturnDTO, uriComponentsBuilder);
    }

    @PostMapping(value = "/paypal-return/{id}")
    public ResponseEntity<?> processPaypal(@Valid PaypalReturnDTO paypalReturnDTO,
                                           @PathVariable("id") Long shoppingId,
                                           UriComponentsBuilder uriComponentsBuilder) {
        return process(shoppingId, paypalReturnDTO, uriComponentsBuilder);
    }

    private ResponseEntity<?> process(Long shoppingId,
                                      GatewayPaymentReturnDTO gatewayPaymentReturnDTO,
                                      UriComponentsBuilder uriComponentsBuilder) {
        Shopping shopping = shoppingRepository.findById(shoppingId).get();
        shopping.addTransaction(gatewayPaymentReturnDTO);
        shoppingRepository.save(shopping);

        if (shopping.successfullyProcessed()) {
            events.forEach(event -> event.process(shopping));
        } else {
            emailFail.failShopping(shopping, uriComponentsBuilder);
        }
        
        return  ResponseEntity.ok().build();
    }
}
