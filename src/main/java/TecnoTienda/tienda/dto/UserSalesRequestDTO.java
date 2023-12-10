package TecnoTienda.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for the request of the sales of a user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSalesRequestDTO {
    private int idUser;
}
