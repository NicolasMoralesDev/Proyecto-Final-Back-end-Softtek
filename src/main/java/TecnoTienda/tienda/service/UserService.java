package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.ChangePasswordRequestDTO;
import TecnoTienda.tienda.dto.UserDTO;
import TecnoTienda.tienda.entity.User;

import java.util.List;

public interface UserService  {

    public User findById(int id);

    public User saveUser(User user);

    public List<User> findAll();

    /**
     * Method for change the password of the user.
     * @param changePasswordRequest the request with the userId, the current password and the new password.
     */
    public void changePassword(ChangePasswordRequestDTO changePasswordRequest);
}
