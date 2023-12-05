package TecnoTienda.tienda.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for now
                .cors(AbstractHttpConfigurer::disable) // Disable CORS protection for now
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/api/public/**").permitAll() // Permit all requests to /api/public/**
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permit all OPTIONS requests
                                .requestMatchers("/api/user/**").authenticated() // Permit authenticated requests to /api/user/**
                                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Permit requests with ADMIN role to /api/admin/**
                                .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) // Disable sessions. JWT is stateless.
                .authenticationProvider(authenticationProvider) // Set the authentication provider
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add the JWT authentication filter
        return http.build(); // Build the security filter chain
    }

}
