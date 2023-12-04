package TecnoTienda.tienda.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;


@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // Column "TEXT type
    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;

    private String category;

    private String brand;

    @Column(name = "img_url")
    private String imageUrl;

    private String status;

    public Product(){
        this.status = "on";
    }

}
