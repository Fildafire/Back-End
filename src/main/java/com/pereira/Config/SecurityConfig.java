package com.pereira.Config;

import com.pereira.Services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register").permitAll()  // Allow access to login/register
                .requestMatchers("/admin/**").hasRole("ADMIN")      // Admin-only access
                .requestMatchers("/farmer/**").hasRole("FARMER")    // Farmer-only access
                .anyRequest().authenticated()                       // All other requests need authentication
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")  // Custom login page
                    .permitAll()  // Allow all to access the login page
            )
            .logout(logout ->
                logout
                    .permitAll()  // Allow all to log out
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
