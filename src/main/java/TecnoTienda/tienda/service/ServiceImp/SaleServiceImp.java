package TecnoTienda.tienda.service.ServiceImp;


import TecnoTienda.tienda.dao.IItemDao;
import TecnoTienda.tienda.dao.ISaleDao;
import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.dto.*;
import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.entity.Sale;
import TecnoTienda.tienda.entity.User;
import TecnoTienda.tienda.mappers.SaleRowMapper;
import TecnoTienda.tienda.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImp implements SaleService {

    @Autowired
    ISaleDao saleDao;

    @Autowired
    UserServiceImp userService;

    @Autowired
    IItemDao itemDao;

    @Autowired
    IProductDao productDao;

    @Autowired
    SaleRowMapper saleRowMapper;

    @Override
    public SaleDTO saveSale(CreateSaleRequestDTO requestDTO){
        System.out.println("requestDTO = " + requestDTO);
        User user = userService.findById(requestDTO.getIdUser());
        Sale newSale = new Sale();

        for (Item item : requestDTO.getItemList()) {
            Item i = new Item();
            i.setProduct(productDao.findById(item.getProduct().getId()).get());
            i.setAmount(item.getAmount());
            i.setSale(newSale);
            newSale.getItemList().add(i);
        }

        newSale.setAddress(requestDTO.getAddress());
        newSale.setPhone(requestDTO.getPhone());
        newSale.setUser(user);

        Integer newSaleId = saleDao.save(newSale).getId();
        Sale sale = saleDao.findById(newSaleId).get();

        return saleRowMapper.saleToSaleDTO(sale);
    }

    @Override
    public UserSalesResponseDTO saleByUserId(UserSalesRequestDTO requestDTO){

        int idUser = requestDTO.getIdUser();
        List<Sale> sales = saleDao.saleByUserId(idUser);

        UserSalesResponseDTO response = new UserSalesResponseDTO();
        response.setIdUser(idUser);

        // Se recorre la lista de ventas y se crea un SaleDTO por cada venta
        List<SaleDTO> saleDTOS = sales.stream().map(sale -> {
            return saleRowMapper.saleToSaleDTO(sale);
        }).collect(Collectors.toList());

        response.setSaleList(saleDTOS);

        return response;
    }
}
