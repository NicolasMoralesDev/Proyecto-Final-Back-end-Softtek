package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.SaleDTO;
import TecnoTienda.tienda.entity.Sale;

import java.util.List;

public interface SaleService {

    public Sale saveSale(Sale sale);

    public SaleDTO saleByUserId(int id);
}
