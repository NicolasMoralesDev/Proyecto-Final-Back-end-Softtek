package TecnoTienda.tienda.dto;

import TecnoTienda.tienda.entity.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleRequestDTO {

    private List<Item> itemList = new ArrayList<>();

    private int idUser;

    private String address;

    private String phone;
}
