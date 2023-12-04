package TecnoTienda.tienda.service;

import TecnoTienda.tienda.entity.ChangePasswordRequest;
import TecnoTienda.tienda.entity.User;

import java.util.List;

public interface UserService  {

    public User findById(int id);

    public User saveUser(User user);

    public List<User> findAll();

    public void changePassword(ChangePasswordRequest changePasswordRequest);
}
