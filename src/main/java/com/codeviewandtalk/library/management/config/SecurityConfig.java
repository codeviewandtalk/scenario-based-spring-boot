package com.codeviewandtalk.library.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers("/api/v1").permitAll();
            authorize.requestMatchers("/api/books/by-author").hasRole("ADMIN")
                    .requestMatchers("/api/books/{id}").hasRole("USER")
                    .anyRequest()
                    .authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    UserDetailsService userDetailsService() {

        UserDetails jhon = User.builder().username("jhon").password(passwordEncoder()
                        .encode("password"))
                .roles("USER")
                .build();

        UserDetails sam = User.builder().username("sam").password(passwordEncoder()
                        .encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(jhon, sam);
    }
}
