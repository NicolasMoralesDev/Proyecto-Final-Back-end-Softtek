package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class UserController {

    @Autowired
    UserService userService;
}
