package TecnoTienda.tienda.service;


import TecnoTienda.tienda.dto.CreateSaleRequestDTO;
import TecnoTienda.tienda.dto.CreateSaleResponseDTO;
import TecnoTienda.tienda.dto.SaleDTO;
import TecnoTienda.tienda.entity.Sale;
import java.util.List;

public interface SaleService {
    CreateSaleResponseDTO saveSale(CreateSaleRequestDTO requestDTO);
    SaleDTO saleByUserId(int id);
}
