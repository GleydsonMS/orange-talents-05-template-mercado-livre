package br.com.zup.mercadolivre.security;

import br.com.zup.mercadolivre.users.User;
import br.com.zup.mercadolivre.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Authentication implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);
        if(user.isPresent()) {
            return user.get();
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
