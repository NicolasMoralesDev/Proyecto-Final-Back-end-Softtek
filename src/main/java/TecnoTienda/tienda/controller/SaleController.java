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
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    SaleService saleService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;


    @Operation(summary = "Endpoint de acceso Rol Usuario, Guarda una orden ")
    @PostMapping("/sale/save")
    public ResponseEntity<?> saveSale(@RequestBody CreateSaleRequestDTO requestDTO) {
        SaleDTO saleDTO = saleService.saveSale(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleDTO);
    }

    @Operation(summary = "Endpoint para traer las ventas de un Usuario")
    @PostMapping("/sale/all")
    public ResponseEntity<?> getSaleByUser(@RequestBody UserSalesRequestDTO requestDTO) {
        UserSalesResponseDTO response = saleService.saleByUserId(requestDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
