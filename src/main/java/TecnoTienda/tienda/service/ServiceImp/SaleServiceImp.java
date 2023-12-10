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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImp implements SaleService {

    @Autowired
    ISaleDao saleDao;

    @Autowired
    UserService userService;

    @Autowired
    SaleMapper saleMapper;

    @Override
    public SaleDTO saveSale(CreateSaleRequestDTO requestDTO){
        // pasar de DTO a ENTITY y buscar en la base de datos
        User user = userService.findById(requestDTO.getIdUser());

        Sale newSale = saleMapper.saleRequestDtoToSale(requestDTO);
        newSale.setUser(user);

        return saleMapper.saleToSaleDTO(saleDao.save(newSale));
    }

    @Override
    public SalePaginationDTO saleByUserId(UserSalesRequestDTO requestDTO, int page){

        int idUser = requestDTO.getIdUser();
        Pageable pageable = PageRequest.of(page, 10);
        SalePaginationDTO salePaginationDTO = new SalePaginationDTO();
        salePaginationDTO.setPage(page);

        Page<Sale> saleList = saleDao.saleByUserIdPageable(idUser, pageable);

        salePaginationDTO.setSales(saleMapper.saleListToSaleDTOList(saleList.getContent()));
        salePaginationDTO.setTotal(saleList.getTotalPages());
        return salePaginationDTO;
    }

    @Override
    public SalePaginationDTO getAllSales(int page){
        Pageable pageable = PageRequest.of(page, 10);
        SalePaginationDTO salePaginationDTO = new SalePaginationDTO();
        salePaginationDTO.setPage(page);

        Page<Sale> saleList = saleDao.findAllPage(pageable);
        salePaginationDTO.setSales(saleMapper.saleListToSaleDTOList(saleList.getContent()));
        salePaginationDTO.setTotal(saleList.getTotalPages());
        return salePaginationDTO;
    }
}
