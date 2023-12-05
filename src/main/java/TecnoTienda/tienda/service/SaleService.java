package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.CreateSaleRequestDTO;
import TecnoTienda.tienda.dto.CreateSaleResponseDTO;
import TecnoTienda.tienda.entity.Sale;

public interface SaleService {

    CreateSaleResponseDTO saveSale(CreateSaleRequestDTO requestDTO);

    CreateSaleRequestDTO saleByUserId(int id);
}
