package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.dto.AuthenticationResponseDTO;
import TecnoTienda.tienda.dto.ChangePasswordRequestMailDTO;
import TecnoTienda.tienda.dto.LoginRequestDTO;
import TecnoTienda.tienda.dto.RecoverDTO;
import TecnoTienda.tienda.dto.RegisterRequestDTO;
import TecnoTienda.tienda.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
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
    
    @Operation(summary = "Endpoint de acceso Publico, para loguearse")
    @PostMapping("/login")

    public ResponseEntity<AuthenticationResponseDTO> login(
            @RequestBody LoginRequestDTO request
    ){
        return ResponseEntity.ok(authService.login(request));
    }
    
     @Operation(summary = "Endpoint de acceso Publico, para recuperar password")
     @PostMapping("/recover")
    public ResponseEntity<String> recoverPassword(
            @RequestBody RecoverDTO request
    ){
        return ResponseEntity.ok(authService.recover(request));
    }
    
    
      @Operation(summary = "Endpoint de acceso Publico, para restablecer password con correo")
     @PostMapping("/recover/password")
    public ResponseEntity<String> recoverPasswordEmail(
            @RequestBody ChangePasswordRequestMailDTO request
    ){
        return ResponseEntity.ok(authService.recoverPasswordEmail(request));
    }
}
