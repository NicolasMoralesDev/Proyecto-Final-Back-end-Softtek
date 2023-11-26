package TecnoTienda.tienda.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private double price;

    private String category;

    private String brand;

    @Column(name = "img_url")
    private String imageUrl;

    private boolean enabled;

    public  Product(){
        this.enabled = true;
    }
}
