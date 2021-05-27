package br.com.zup.mercadolivre.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO) {
        String passwordEncoded = passwordEncoder.encode(userDTO.getPassword());
        User user = userDTO.toModel(passwordEncoded);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
