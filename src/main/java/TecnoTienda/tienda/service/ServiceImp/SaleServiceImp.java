package TecnoTienda.tienda.service.ServiceImp;


import TecnoTienda.tienda.dao.ISaleDao;
import TecnoTienda.tienda.dto.*;
import TecnoTienda.tienda.entity.Sale;
import TecnoTienda.tienda.entity.User;
import TecnoTienda.tienda.mappers.SaleMapper;
import TecnoTienda.tienda.service.SaleService;
import TecnoTienda.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


@Service
public class SaleServiceImp implements SaleService {

    @Autowired
    ISaleDao saleDao;

    @Autowired
    UserService userService;

    @Autowired
    SaleMapper saleMapper;

    /**
     * Method for save a sale in the database.
     * @param requestDTO the request with the userId, address, phone, and the list of products with the amount of each one.
     * @return SaleDTO, the sale saved.
     */
    @Override
    public SaleDTO saveSale(CreateSaleRequestDTO requestDTO){

        // Find the user in the database.
        User user = userService.findById(requestDTO.getIdUser());

        // Create the sale mapping the request to a sale entity.
        Sale newSale = saleMapper.saleRequestDtoToSale(requestDTO);

        // Set the user to the sale.
        newSale.setUser(user);

        // Save the sale in the database and return the sale mapped to a SaleDTO.
        return saleMapper.saleToSaleDTO(saleDao.save(newSale));
    }

    /**
     * Method for get all the sales of a user.
     * @param requestDTO the request with the userId.
     * @param page int, the page number.
     * @return SalePaginationDTO, the sales of the user.
     */
    @Override
    public SalePaginationDTO saleByUserId(UserSalesRequestDTO requestDTO, int page){

        // Getting the user id.
        int idUser = requestDTO.getIdUser();

        // Initializing the pagination.
        Pageable pageable = PageRequest.of(page, 10);

        // Creating the response.
        SalePaginationDTO salePaginationDTO = new SalePaginationDTO();

        // Setting the page number.
        salePaginationDTO.setPage(page);

        // Getting the sales of the user paginated.
        Page<Sale> saleList = saleDao.saleByUserIdPageable(idUser, pageable);

        // Setting the sales of the user.
        salePaginationDTO.setSales(saleMapper.saleListToSaleDTOList(saleList.getContent()));

        // Setting the total pages.
        salePaginationDTO.setTotal(saleList.getTotalPages());

        // Returning the response.
        return salePaginationDTO;
    }

    /**
     * Method for get all the sales paginated. Admin role required.
     * @param page int, the page number.
     * @return SalePaginationDTO, the sales.
     */
    @Override
    public SalePaginationDTO getAllSales(int page){

        // Initializing the pagination.
        Pageable pageable = PageRequest.of(page, 10);

        // Creating the response.
        SalePaginationDTO salePaginationDTO = new SalePaginationDTO();

        // Setting the page number.
        salePaginationDTO.setPage(page);

        // Getting the sales paginated.
        Page<Sale> saleList = saleDao.findAllPage(pageable);

        // Setting the sales.
        salePaginationDTO.setSales(saleMapper.saleListToSaleDTOList(saleList.getContent()));

        // Setting the total pages.
        salePaginationDTO.setTotal(saleList.getTotalPages());

        // Returning the response.
        return salePaginationDTO;
    }

}
