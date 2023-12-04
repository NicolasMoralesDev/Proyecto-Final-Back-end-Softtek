package TecnoTienda.tienda.controller;


import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Sale;
import TecnoTienda.tienda.entity.User;
import TecnoTienda.tienda.service.SaleService;
import TecnoTienda.tienda.service.ProductService;
import TecnoTienda.tienda.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SaleController {

    @Autowired
    SaleService saleService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @Operation(summary = "Endpoint de acceso Rol Usuario, Guarda una orden ")
    @PostMapping("/sale/save")
    public ResponseEntity<?> saveSale(@RequestBody List<Item> itemList,
                                      @PathVariable("id") int id){
        try {
            User user = userService.findById(id);
            Sale sale = new Sale();
            for (Item item : itemList) {
                Item i = new Item();
                i.setProduct(productService.findById(item.getProduct().getId()).get());
                i.setAmount(item.getAmount());
                i.setSale(sale);
                sale.getItemList().add(i);
            }
            sale.setUser(user);
            saleService.saveSale(sale);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
