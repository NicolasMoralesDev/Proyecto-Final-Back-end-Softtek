package TecnoTienda.tienda.controller;


import TecnoTienda.tienda.dto.*;
import TecnoTienda.tienda.service.SaleService;
import TecnoTienda.tienda.service.ProductService;
import TecnoTienda.tienda.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    SaleService saleService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;


    @Operation(summary = "Endpoint de acceso Rol Usuario, Guarda una orden ")
    @PostMapping("/user/sale/save")
    public ResponseEntity<?> saveSale(@RequestBody CreateSaleRequestDTO requestDTO) {
        SaleDTO saleDTO = saleService.saveSale(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleDTO);
    }

    @Operation(summary = "Endpoint para traer las ventas de un Usuario")
    @PostMapping("/user/sale/all")
    public ResponseEntity<?> getSaleByUser(@RequestBody UserSalesRequestDTO requestDTO, @RequestParam int page) {
        SalePaginationDTO response = saleService.saleByUserId(requestDTO, page);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @Operation(summary = "Endpoint de administración para traer todas las ventas")
    @GetMapping("/admin/sale/all")
    public ResponseEntity<?> getAllSales(@RequestParam int page) {
        try {
            SalePaginationDTO paginationDTO = saleService.getAllSales(page);
            return ResponseEntity.ok().body(paginationDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
