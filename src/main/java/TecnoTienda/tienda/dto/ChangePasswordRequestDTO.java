package TecnoTienda.tienda.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequestDTO {

    private int userId;
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
