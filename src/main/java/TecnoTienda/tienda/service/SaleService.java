package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.*;
import TecnoTienda.tienda.entity.Sale;

public interface SaleService {

    SaleDTO saveSale(CreateSaleRequestDTO requestDTO);

    UserSalesResponseDTO saleByUserId(UserSalesRequestDTO requestDTO);
}
