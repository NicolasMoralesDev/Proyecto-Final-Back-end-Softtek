
package TecnoTienda.tienda.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 * @author Nico Morales
 */
@Setter
@Getter
public class MercadoPagoDTO {
    
    private String id;
    private BigDecimal price;
    private int amount;
    
}
