
package TecnoTienda.tienda.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nico Morales
 */

@Getter
@Setter
public class RecoverDTO {
    
    private String email;

    public RecoverDTO() {
    }

    public RecoverDTO(String email) {
        this.email = email;
    }
    
    
}
