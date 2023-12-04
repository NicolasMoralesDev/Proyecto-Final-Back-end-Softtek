package TecnoTienda.tienda.service.ServiceImp;


import TecnoTienda.tienda.dao.IUserDao;
import TecnoTienda.tienda.entity.ChangePasswordRequest;
import TecnoTienda.tienda.entity.User;
import TecnoTienda.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp  implements UserService{

    @Autowired
    IUserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<User> findAll(){
        return userDao.findAll();
    }

    @Override
    public User saveUser(User user){
        return userDao.save(user);
    }

    /**
     * Method for change the password of the user.
     * @param request the request with the userId, the current password and the new password.
     */
    @Override
    public void changePassword(ChangePasswordRequest request){
        User user = userDao.findById(request.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User not found in database"));
        if(!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
            throw new IllegalStateException("Wrong password");
        }
        if(!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("Password are not the same");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userDao.save(user);
    }


}
