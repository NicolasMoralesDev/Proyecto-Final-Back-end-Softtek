package TecnoTienda.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSalesResponseDTO {
    int idUser;
    List<SaleDTO> saleList = new ArrayList<>();
}
