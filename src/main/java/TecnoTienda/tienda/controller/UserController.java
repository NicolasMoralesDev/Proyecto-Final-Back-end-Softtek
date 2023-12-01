package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.entity.User;
import TecnoTienda.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/save/user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        try{
            User userSaved = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
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
}
