package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.entity.AuthenticationResponse;
import TecnoTienda.tienda.entity.LoginRequest;
import TecnoTienda.tienda.entity.RegisterRequest;
import TecnoTienda.tienda.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {
    @Mock
    private AuthService authService;
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // para inicializar los campos anotados con @Mock
    }

    @Test
    public void testRegister(){
        // Simula el comportamiento del servicio de autenticación al registrarse
        RegisterRequest registerRequest = new RegisterRequest(/* ... */);
        AuthenticationResponse expectedResponse = AuthenticationResponse.builder()
                .token("your-token-value")
                .build();
        when(authService.register(registerRequest)).thenReturn(expectedResponse);

        // Llama al método del controlador y verifica la respuesta
        ResponseEntity<AuthenticationResponse> responseEntity = authController.register(registerRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        // Verifica que el servicio de autenticación fue llamado con los parámetros correctos
        verify(authService, times(1)).register(registerRequest);

    }
    @Test
    public void testLogin() {
        // Simula el comportamiento del servicio de autenticación al iniciar sesión
        LoginRequest loginRequest = new LoginRequest(/* ... */);
        AuthenticationResponse expectedResponse = AuthenticationResponse.builder()
                .token("your-token-value")
                .build();
        when(authService.login(loginRequest)).thenReturn(expectedResponse);

        // Llama al método del controlador y verifica la respuesta
        ResponseEntity<AuthenticationResponse> responseEntity = authController.login(loginRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        // Verifica que el servicio de autenticación fue llamado con los parámetros correctos
        verify(authService, times(1)).login(loginRequest);
    }
}
