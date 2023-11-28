package TecnoTienda.tienda.controller;


import TecnoTienda.tienda.dao.IItemDao;
import TecnoTienda.tienda.dao.ISaleDao;
import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Sale;
import TecnoTienda.tienda.service.SaleService;
import TecnoTienda.tienda.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SaleController {

    @Autowired
    SaleService saleService;
    @Autowired
    ProductService productService;


    @PostMapping("/sale/save")
    @Operation(summary = "Endpoint publico, Crea ordenes")
    @PostMapping("/order/save")
    public ResponseEntity<?> saveOrder(@RequestBody List<Item> itemList){
        try {
            Sale sale = new Sale();
            for (Item item : itemList) {
                Item i = new Item();
                i.setProduct(productService.findById(item.getProduct().getId()));
                i.setAmount(item.getAmount());
                i.setSale(sale);
                sale.getItemList().add(i);
            }
            saleService.saveSale(sale);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
