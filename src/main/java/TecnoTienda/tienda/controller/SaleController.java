package TecnoTienda.tienda.controller;


import TecnoTienda.tienda.dao.IItemDao;
import TecnoTienda.tienda.dao.ISaleDao;
import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Sale;
import TecnoTienda.tienda.service.SaleService;
import TecnoTienda.tienda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saveOrder")
public class SaleController {

    @Autowired
    SaleService saleService;
    @Autowired
    ProductService productService;

    @PostMapping("")
    public String saveOrder(@RequestBody List<Item> itemList){
        Sale sale = new Sale();
        for(Item item:itemList){
            Item i = new Item();
            i.setProduct(productService.findById(item.getProduct().getId()));
            i.setAmount(item.getAmount());
            i.setSale(sale);
            sale.getItemList().add(i);
        }
        saleService.saveSale(sale);
        return "Venta guardada";
    }
}
