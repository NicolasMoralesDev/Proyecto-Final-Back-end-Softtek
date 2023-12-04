package TecnoTienda.tienda.service.ServiceImp;

import TecnoTienda.tienda.config.JwtService;
import TecnoTienda.tienda.dao.IUserDao;
import TecnoTienda.tienda.dto.AuthenticationResponse;
import TecnoTienda.tienda.dto.LoginRequest;
import TecnoTienda.tienda.dto.RegisterRequest;
import TecnoTienda.tienda.entity.*;
import TecnoTienda.tienda.exceptions.UserValidationException;
import TecnoTienda.tienda.role.Role;
import TecnoTienda.tienda.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final IUserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse login(LoginRequest request){

        // User authentication. If the user is not found or the password is incorrect, an exception is thrown.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Once the user is authenticated, the token is generated. Here we save the user in a variable to use it later.
        var user = userDao.findByEmail(request.getEmail()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        // Generate token with extra claims
        var jwtToken = generateTokenWithExtraClaims(user);

        // Return the token in a AuthenticationResponse object. This object is converted to JSON and returned to the client.
        // it only contains the token.
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        // Validate user data
        validateUser(request);

        // Create user
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encode password
                .role(Role.ROLE_USER)
                .build();
        userDao.save(user);

        // Generate token with extra claims
        String jwtToken = generateTokenWithExtraClaims(user);

        // Return the token in a AuthenticationResponse object. This object is converted to JSON and returned to the client.
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    /**
     * This method validate the user data. If the data is not valid, an exception is thrown.
     * @param request RegisterRequest, contains the user data.
     */
    private void validateUser(RegisterRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new UserValidationException("Email cannot be empty.");
        }

        if (userDao.existsByEmail(request.getEmail())) {
            throw new UserValidationException("Email already exists: " + request.getEmail() + ".");
        }

        if (request.getPassword().length() < 8) {
            throw new UserValidationException("Password must be at least 8 characters long.");
        }

        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            throw new UserValidationException("First name cannot be empty.");
        }

        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            throw new UserValidationException("Last name cannot be empty.");
        }
    }

    /**
     * This method generate a token with extra claims. The extra claims are the user data.
     * @param user User, contains the user data.
     * @return String, the token.
     */
    private String generateTokenWithExtraClaims(User user) {
        // Before generate the token, we need to create a map with the extra claims.
        Map<String, Object> extraClaims = new HashMap<>();

        // The extra claims are the user data. This will be in the payload of the token.
        extraClaims.put("id", user.getId());
        extraClaims.put("firstName", user.getFirstName());
        extraClaims.put("lastName", user.getLastName());
        extraClaims.put("role", user.getRole().name());

        // Generate token with extra claims
        return jwtService.generateToken(extraClaims, user);
    }
}
