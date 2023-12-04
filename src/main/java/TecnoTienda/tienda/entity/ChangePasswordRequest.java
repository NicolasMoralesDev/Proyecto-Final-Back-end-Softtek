package TecnoTienda.tienda.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    private int userId;
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
