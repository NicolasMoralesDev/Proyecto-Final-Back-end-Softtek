package TecnoTienda.tienda.dto;

import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.SaleStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for the request of the creation of a sale.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleRequestDTO {

    private int idUser;

    private List<Item> itemList = new ArrayList<>();

    private String address;
    @Enumerated(EnumType.STRING)
    private SaleStatus status;
    private String phone;
}
