package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // This bean defines the security rules for your application
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Enable CORS (Cross-Origin Resource Sharing) settings
            .cors(withDefaults())
            // Disable CSRF (Cross-Site Request Forgery) for stateless APIs (JWT-based)
            .csrf(csrf -> csrf.disable())
            // Define URL access rules
            .authorizeHttpRequests(authz -> authz
                // Allow public access to any URL under /public/*
                .requestMatchers("/public/**").permitAll()
                // Only allow users with ADMIN role to access DELETE requests
                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            // Enable default form-based login (i.e., Spring Security's default login page)
            .formLogin(withDefaults())
            // Enable HTTP Basic authentication (username/password in the HTTP header)
            .httpBasic(withDefaults());

        return http.build();
    }

    // This bean creates an in-memory user store with predefined users
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Define a regular user with USER role
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))  // Use password encoder for secure storage
            .roles("USER")
            .build();

        // Define an admin user with ADMIN and USER roles
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("adminpassword"))  // Use password encoder for secure storage
            .roles("ADMIN", "USER")
            .build();

        // Use InMemoryUserDetailsManager to manage users in-memory (good for testing)
        return new InMemoryUserDetailsManager(user, admin);
    }

    // This bean provides password encoding using BCrypt (a secure hashing algorithm)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This bean defines the CORS configuration to allow cross-origin requests
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);  // Allow credentials (cookies, etc.) in CORS requests
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));  // Allow specific frontend origin
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));  // Allowed headers
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Allowed HTTP methods

        // Register the CORS configuration for all paths (/**)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
