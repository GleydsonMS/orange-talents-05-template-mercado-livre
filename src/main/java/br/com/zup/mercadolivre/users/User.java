package br.com.zup.mercadolivre.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @NotNull
    @PastOrPresent
    private Instant createdAt;

    @Deprecated
    private User () {}

    public User(@NotBlank @Email String login,
                @NotBlank @Size(min = 6, max = 100) String password) {
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.createdAt = Instant.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", created_at=" + createdAt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
