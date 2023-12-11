package TecnoTienda.tienda.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for the request of the change password.
 */
@Getter
@Setter
@Builder
public class ChangePasswordRequestDTO {

    private int userId;
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
