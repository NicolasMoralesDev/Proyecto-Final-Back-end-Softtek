package TecnoTienda.tienda.service.ServiceImp;


import TecnoTienda.tienda.dao.IUserDao;
import TecnoTienda.tienda.entity.User;
import TecnoTienda.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp  implements UserService{

    @Autowired
    IUserDao userDao;


    @Override
    public User findById(int id) {
        return userDao.findById(id).get();
    }
}
