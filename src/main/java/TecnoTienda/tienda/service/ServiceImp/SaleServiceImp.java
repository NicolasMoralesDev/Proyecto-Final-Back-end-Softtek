package TecnoTienda.tienda.service.ServiceImp;


import TecnoTienda.tienda.dao.IItemDao;
import TecnoTienda.tienda.dao.ISaleDao;
import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.dto.SaleDTO;
import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.entity.Sale;
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
    IItemDao itemDao;

    @Autowired
    IProductDao productDao;
    @Override
    public Sale saveSale(Sale sale){
        return saleDao.save(sale);
    }

    @Override
    public SaleDTO saleByUserId(int id){
        List<Sale> saleByIdUser = saleDao.saleByUserId(id);
        List<Item> itemList = new ArrayList<>();
        SaleDTO saleDto = new SaleDTO();
        for(Sale sale : saleByIdUser){
            itemList.addAll(itemDao.getItemBySaleId(sale.getId()).stream().collect(Collectors.toList()));
        }
        for(Item i : itemList){
            Item item = new Item();
            Product product = productDao.findById(i.getProduct().getId()).get();
            item.setId(i.getId());
            item.setProduct(product);
            item.setAmount(i.getAmount());
            saleDto.getItemList().add(item);
        }
        return saleDto;
    }
}
