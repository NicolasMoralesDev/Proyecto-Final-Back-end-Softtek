package TecnoTienda.tienda.mappers;

import TecnoTienda.tienda.dto.UserDTO;
import TecnoTienda.tienda.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDTO userToUserDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(userDto.getEmail());
        return userDto;
    }
    public List<UserDTO> listUserToListUserDto(List<User> listUser){
        List<UserDTO> listUserDto = new ArrayList<>();
        for(User u : listUser){
            listUserDto.add(this.userToUserDto(u));
        }
        return listUserDto;
    }
}
