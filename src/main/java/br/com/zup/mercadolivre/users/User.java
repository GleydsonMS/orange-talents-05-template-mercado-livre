package br.com.zup.mercadolivre.users;

import br.com.zup.mercadolivre.users.profiles.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getLogin().equals(user.getLogin());
    }

    @Override
    public int hashCode() {
        return getLogin().hashCode();
    }

    public String getEmail() {
        return this.login;
    }
}
