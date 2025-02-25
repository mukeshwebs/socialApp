package com.hashedin.huspark.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCryptPasswordEncoder
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF for simplicity
                .authorizeHttpRequests()  // Use authorizeHttpRequests for newer Spring Security versions
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()  // Allow access to H2 console without authentication
                .requestMatchers(new AntPathRequestMatcher("/auth/login")).permitAll()  // Allow unauthenticated access to login
                .requestMatchers(new AntPathRequestMatcher("/auth/signup")).permitAll()  // Allow unauthenticated access to signup
//                .requestMatchers(new AntPathRequestMatcher("/posts/**")).permitAll()  // Allow unauthenticated access to posts
                .requestMatchers(new AntPathRequestMatcher("/comments/**")).permitAll()  // Allow unauthenticated access to comments
                .requestMatchers(new AntPathRequestMatcher("/follow/**")).permitAll()  // Allow unauthenticated access to follow
                .requestMatchers(new AntPathRequestMatcher("/likeComment/**")).permitAll()  // Allow unauthenticated access to like comments
                .requestMatchers(new AntPathRequestMatcher("/likePost/**")).permitAll()  // Allow unauthenticated access to like posts
                .anyRequest().authenticated()  // Authenticate other requests
                .and()
                .formLogin().disable();  // Disable form login

        http.headers().frameOptions().sameOrigin();  // Allow H2 console to be embedded in the same origin

        return http.build();  // Return the configured HttpSecurity instance
    }
}
