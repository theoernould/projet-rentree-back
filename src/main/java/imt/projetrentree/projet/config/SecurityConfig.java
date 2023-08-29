package imt.projetrentree.projet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Désactiver CSRF pour H2 console
                .headers().frameOptions().disable()  // Autoriser l'accès à la console H2
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
        return http.build();
    }
}
