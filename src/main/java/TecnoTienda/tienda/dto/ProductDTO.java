package TecnoTienda.tienda.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private int id;

    private String name;

    private String description;

    private BigDecimal price;

    private String category;

    private String brand;

    private String imageUrl;

    private int stock;

}
