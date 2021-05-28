package br.com.zup.mercadolivre.security;

import br.com.zup.mercadolivre.users.User;
import br.com.zup.mercadolivre.users.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilterAuthentication extends OncePerRequestFilter {

    private TokenCreate tokenCreate;
    private UserRepository userRepository;

    public TokenFilterAuthentication(TokenCreate tokenCreate, UserRepository userRepository) {
        this.tokenCreate = tokenCreate;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        String token = tokenRecover(httpServletRequest);

        boolean validate = tokenCreate.isValidadeToken(token);
        if (validate) {
            userAuthenticate(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void userAuthenticate(String token) {
        Long userId = tokenCreate.getUserId(token);
        User user = userRepository.findById(userId).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String tokenRecover(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
