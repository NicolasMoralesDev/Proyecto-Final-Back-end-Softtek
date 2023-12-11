package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.*;
import TecnoTienda.tienda.entity.Sale;

public interface SaleService {

    /**
     * Method for save a sale. It will be used when a user buy a product.
     * The request must contain: the userId, address, phone, and the list of products with the amount of each one.
     * @param requestDTO the request with the userId, address, phone, and the list of products with the amount of each one.
     * @return SaleDTO, the sale saved.
     */
    SaleDTO saveSale(CreateSaleRequestDTO requestDTO);


    UserSalesResponseDTO saleByUserId(UserSalesRequestDTO requestDTO);

    

    /**
     * Method for get all the sales of a user. The request must contain the userId.
     * @param requestDTO the request with the userId.
     * @param page int, the page number.
     * @return SalePaginationDTO, the sales of the user.
     */
    SalePaginationDTO saleByUserId(UserSalesRequestDTO requestDTO, int page);


    /**
     * Method for get all the sales paginated. Admin role required.
     * @param page int, the page number.
     * @return SalePaginationDTO, the sales.
     */
    SalePaginationDTO getAllSales(int page);
}
