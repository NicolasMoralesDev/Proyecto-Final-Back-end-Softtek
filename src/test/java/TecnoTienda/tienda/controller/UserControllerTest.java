package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.dto.AuthenticationResponseDTO;
import TecnoTienda.tienda.dto.UserDTO;
import TecnoTienda.tienda.entity.User;
import TecnoTienda.tienda.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;


    @Test
    public void testGetUsers() {
        // Configuración del servicio simulado
        List<User> userList = Collections.singletonList(new User());
        when(userService.findAll()).thenReturn(userList);

        // Llamada al controlador y verificación de resultados
        ResponseEntity<List<UserDTO>> responseEntity = userController.getUsers();
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(userList, responseEntity.getBody());

        // Verifica que el método del servicio se haya llamado según lo esperado
        verify(userService, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        // Configuración del servicio simulado
        int userId = 1;
        User user = new User();
        when(userService.findById(userId)).thenReturn(user);

        // Llamada al controlador y verificación de resultados
        ResponseEntity<UserDTO> responseEntity = userController.getUserById(userId);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());

        // Verifica que el método del servicio se haya llamado según lo esperado
        verify(userService, times(1)).findById(userId);
    }
}
