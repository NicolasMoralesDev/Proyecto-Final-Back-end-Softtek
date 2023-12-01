package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.entity.AuthenticationResponse;
import TecnoTienda.tienda.entity.LoginRequest;
import TecnoTienda.tienda.entity.RegisterRequest;
import TecnoTienda.tienda.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    /**
     * Endpoint for register a new user in the system.
     * @param request RegisterRequest, contains the user data.
     * @return ResponseEntity<AuthenticationResponse>, contains the token or an error.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        AuthenticationResponse registerResponse = authService.register(request);
        return ResponseEntity.ok(registerResponse);
    }

    /**
     * Endpoint for login a user in the system.
     * @param request LoginRequest, contains the user data.
     * @return ResponseEntity<AuthenticationResponse>, contains the token or an error.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody LoginRequest request
    ){
        AuthenticationResponse loginResponse = authService.login(request);
        return ResponseEntity.ok(loginResponse);
    }
}
