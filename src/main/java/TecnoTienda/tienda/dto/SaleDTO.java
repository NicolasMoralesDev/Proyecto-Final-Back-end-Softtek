package TecnoTienda.tienda.dto;

import TecnoTienda.tienda.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SaleDTO {

    private List<Item> itemList = new ArrayList<>();
    private int idUser;
}
