
package TecnoTienda.tienda.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nico Morales
 */

@Getter
@Setter
public class ChangePasswordRequestMailDTO {
 
    private int userId;
    private String newPassword;
    private String confirmationPassword;

    public ChangePasswordRequestMailDTO() {
    }
    

    public ChangePasswordRequestMailDTO(int userId, String newPassword, String confirmationPassword) {
        this.userId = userId;
        this.newPassword = newPassword;
        this.confirmationPassword = confirmationPassword;
    }
    
    
    
}
