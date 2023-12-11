package TecnoTienda.tienda.dto;

import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.SaleStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for the request of the creation of a sale.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Integer id;
    private List<Item> itemList = new ArrayList<>();
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    private LocalDate date;
}