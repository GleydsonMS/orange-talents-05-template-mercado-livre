package br.com.zup.mercadolivre.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @Deprecated
    private UserDTO() {}

    public UserDTO(@NotBlank @Email String login,
                   @NotBlank @Size(min = 6, max = 100) String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public User toModel(String passwordEncoded) {
        return new User(login, passwordEncoded);
    }
}
