package br.com.zup.mercadolivre.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    // Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    // Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configuração temporária para o funcionamento do endpoint.
        http.authorizeRequests()
                .antMatchers("/users").permitAll()
                .antMatchers("/categories").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

    // Static resources
    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
