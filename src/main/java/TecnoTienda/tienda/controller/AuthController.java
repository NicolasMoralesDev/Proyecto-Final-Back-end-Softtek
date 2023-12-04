package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.dto.AuthenticationResponseDTO;
import TecnoTienda.tienda.dto.LoginRequestDTO;
import TecnoTienda.tienda.dto.RegisterRequestDTO;
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
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO request
    ){
        AuthenticationResponseDTO registerResponse = authService.register(request);
        return ResponseEntity.ok(registerResponse);
    }

    /**
     * Endpoint for login a user in the system.
     * @param request LoginRequest, contains the user data.
     * @return ResponseEntity<AuthenticationResponse>, contains the token or an error.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody LoginRequestDTO request
    ){
        AuthenticationResponseDTO loginResponse = authService.login(request);
        return ResponseEntity.ok(loginResponse);
    }
}
