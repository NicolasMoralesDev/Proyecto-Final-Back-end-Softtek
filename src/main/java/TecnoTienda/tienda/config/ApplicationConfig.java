package TecnoTienda.tienda.config;

import TecnoTienda.tienda.dao.IUserDao;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DialectOverride;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class for configure the application. Here we set the beans for the application.
 * If you want to add a new bean, you must add it here.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final IUserDao repository;

    /**
     * Bean for get the user details from the database. This bean is used by the authentication provider.
     * @return UserDetailsService, the user details service.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    /**
     * Bean for configure the authentication provider. This bean is used by the authentication manager. Here we
     * set the user details service and the password encoder.
     * @return AuthenticationProvider, the authentication provider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        // Create the authentication provider
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Set the user details service
        authProvider.setUserDetailsService(userDetailsService());

        // Set the password encoder
        authProvider.setPasswordEncoder(passwordEncoder());

        // Return the authentication provider
        return authProvider;
    }

    /**
     * Bean for configure the password encoder. This bean is used by the authentication provider.
     * We use BCryptPasswordEncoder for encode the password.
     * @return PasswordEncoder, the password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for configure the authentication manager. This bean is used by the authentication controller.
     * This bean is necessary because the authentication manager is not created automatically.
     * @param config AuthenticationConfiguration, the authentication configuration.
     * @return AuthenticationManager, the authentication manager.
     * @throws Exception if the authentication manager cannot be created.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

