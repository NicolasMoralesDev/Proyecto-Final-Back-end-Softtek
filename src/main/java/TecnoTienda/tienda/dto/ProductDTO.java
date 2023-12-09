package TecnoTienda.tienda.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class ProductDTO {
    private int id;

    private String name;

    private String description;

    private double price;

    private String category;

    private String brand;

    private String imageUrl;

    private int stock;
}
