package TecnoTienda.tienda.dto;

import TecnoTienda.tienda.entity.Item;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Integer id;
    private List<Item> itemList = new ArrayList<>();
    private String address;
    private String phone;
    private LocalDate date;
}