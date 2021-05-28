package br.com.zup.mercadolivre.security;

import br.com.zup.mercadolivre.security.dtos.AuthDTO;
import br.com.zup.mercadolivre.security.dtos.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenCreate tokenCreate;

    @PostMapping
    public ResponseEntity<TokenDTO>  authenticate(@RequestBody @Valid AuthDTO authDTO) {
        UsernamePasswordAuthenticationToken userLogin = authDTO.toModel();

        try {
            Authentication authentication = authenticationManager.authenticate(userLogin);
            String token = tokenCreate.tokenGenerate(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
