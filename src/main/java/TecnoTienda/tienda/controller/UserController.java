package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.dto.ChangePasswordRequestDTO;
import TecnoTienda.tienda.dto.UserDTO;
import TecnoTienda.tienda.entity.User;
import TecnoTienda.tienda.mappers.UserMapper;
import TecnoTienda.tienda.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Operation(summary = "Endpoint privado, trae todos los usuarios existentes")    
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        try {
            List<UserDTO> listUserDto = userMapper.listUserToListUserDto(userService.findAll());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(listUserDto);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @Operation(summary = "Endpoint privado, trae un usuario por su id")
    @GetMapping("/admin/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id){
        try {
            UserDTO userDto = userMapper.userToUserDto(userService.findById(id));
            return ResponseEntity.status(HttpStatus.FOUND).body(userDto);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation (summary = "Endpoint privado, cambia la contraseña de un usuario")
    @PutMapping("/user/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestDTO request){
        try {
            userService.changePassword(request);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
