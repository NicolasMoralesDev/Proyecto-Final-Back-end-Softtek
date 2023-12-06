
package TecnoTienda.tienda.dto;

import TecnoTienda.tienda.entity.Product;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nico Morales
 */
@Getter
@Setter
@Component
public class ProductPaginationDTO {
    
    private List <ProductDTO> productos;
    private int page;
    private int total;

    public ProductPaginationDTO() {
    }

    public ProductPaginationDTO(List<ProductDTO> productosDto, int page, int total) {
        this.productos = productosDto;
        this.page = page;
        this.total = total;
    }

}

