package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.*;
import TecnoTienda.tienda.entity.Sale;

public interface SaleService {

    SaleDTO saveSale(CreateSaleRequestDTO requestDTO);

    SalePaginationDTO saleByUserId(UserSalesRequestDTO requestDTO, int page);

    SalePaginationDTO getAllSales(int page);
}
