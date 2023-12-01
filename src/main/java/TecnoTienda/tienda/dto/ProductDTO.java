
package TecnoTienda.tienda.dto;

import TecnoTienda.tienda.entity.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Nico Morales
 */
@Getter
@Setter
public class ProductDTO {
    
    private List <Product> productos;
    private int total;

    public ProductDTO() {
    }

    public ProductDTO(List<Product> productos, int total) {
        this.productos = productos;
        this.total = total;
    }
    
    
    
}
