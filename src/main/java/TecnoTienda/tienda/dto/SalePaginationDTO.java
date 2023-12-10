package TecnoTienda.tienda.dto;

import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for the request of the creation of a sale.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalePaginationDTO {
    private List<SaleDTO> sales;
    private int page;
    private int total;

}
