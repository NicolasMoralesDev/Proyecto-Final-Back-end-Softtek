package TecnoTienda.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalePaginationDTO {
    private List<SaleDTO> sales;
    private int page;
    private int total;

}
