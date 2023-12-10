package TecnoTienda.tienda.dto;

import TecnoTienda.tienda.role.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String email;
}
