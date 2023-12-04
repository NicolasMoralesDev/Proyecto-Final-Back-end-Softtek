package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.entity.ChangePasswordRequest;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.entity.User;
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

    @Operation(summary = "Endpoint privado, trae todos los usuarios existentes")    
    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getUsers(){
        try {
            List<User> users = userService.findAll();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @Operation(summary = "Endpoint privado, trae un usuario por su id")
    @GetMapping("/admin/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        try {
            User user = userService.findById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation (summary = "Endpoint privado, cambia la contraseña de un usuario")
    @PutMapping("/user/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request){
        try {
            userService.changePassword(request);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
