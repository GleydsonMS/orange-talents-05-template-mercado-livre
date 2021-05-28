package br.com.zup.mercadolivre.security.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthDTO {

    private String login;
    private String password;

    public AuthDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UsernamePasswordAuthenticationToken toModel() {
        return new UsernamePasswordAuthenticationToken(login, password);
    }
}
