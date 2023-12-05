package TecnoTienda.tienda.service.ServiceImp;


import TecnoTienda.tienda.dao.IItemDao;
import TecnoTienda.tienda.dao.ISaleDao;
import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.dto.CreateSaleRequestDTO;
import TecnoTienda.tienda.dto.CreateSaleResponseDTO;
import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.entity.Sale;
import TecnoTienda.tienda.entity.User;
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
    @Override
    public CreateSaleResponseDTO saveSale(CreateSaleRequestDTO requestDTO){

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

        CreateSaleResponseDTO createSaleResponseDto = new CreateSaleResponseDTO();
        createSaleResponseDto.setId(sale.getId()); // id de la venta
        createSaleResponseDto.setIdUser(sale.getUser().getId());
        createSaleResponseDto.setAddress(sale.getAddress());
        createSaleResponseDto.setPhone(sale.getPhone());

        if (sale.getItemList() != null) {
            for (Item item : sale.getItemList()) {
                Item i = new Item();
                i.getSale().setId(sale.getId());
                i.setId(item.getId());
                i.setProduct(item.getProduct());
                i.setAmount(item.getAmount());
                createSaleResponseDto.getItemList().add(i);
            }
        }

        return createSaleResponseDto;
    }

    @Override
    public CreateSaleRequestDTO saleByUserId(int id){
        List<Sale> saleByIdUser = saleDao.saleByUserId(id);
        List<Item> itemList = new ArrayList<>();
        CreateSaleRequestDTO createSaleRequestDto = new CreateSaleRequestDTO();
        for(Sale sale : saleByIdUser){
            itemList.addAll(itemDao.getItemBySaleId(sale.getId()).stream().collect(Collectors.toList()));
        }
        for(Item i : itemList){
            Item item = new Item();
            Product product = productDao.findById(i.getProduct().getId()).get();
            item.setId(i.getId());
            item.setProduct(product);
            item.setAmount(i.getAmount());
            createSaleRequestDto.getItemList().add(item);
        }
        return createSaleRequestDto;
    }
}
