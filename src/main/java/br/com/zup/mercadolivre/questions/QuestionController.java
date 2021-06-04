package br.com.zup.mercadolivre.questions;

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
public class QuestionController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private Emails emails;

    @PostMapping("/products/{id}/questions")
    public ResponseEntity<?> createQuestion(@RequestBody @Valid QuestionDTO questionDTO,
                                         @PathVariable("id") Long id,
                                         @AuthenticationPrincipal User creator) {

        Product product = productRepository.findById(id).get();
        Question question = questionDTO.toModel(creator, product);
        questionRepository.save(question);

        emails.newQuestion(question);

        return ResponseEntity.ok().build();
    }
}
