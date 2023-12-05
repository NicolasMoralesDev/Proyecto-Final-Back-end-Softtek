package TecnoTienda.tienda.controller;


import TecnoTienda.tienda.dto.CreateSaleRequestDTO;
import TecnoTienda.tienda.dto.CreateSaleResponseDTO;
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
        CreateSaleResponseDTO createSaleResponseDTO = saleService.saveSale(requestDTO);
        System.out.println("createSaleResponseDTO = " + createSaleResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createSaleResponseDTO);
    }

    @Operation(summary = "Endpoint para traer las ventas de un Usuario")
    @GetMapping("/sale/{id}")
    public ResponseEntity<CreateSaleRequestDTO> getSaleByUserId(@PathVariable("id") int id) {
        CreateSaleRequestDTO createSaleRequestDto = saleService.saleByUserId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(createSaleRequestDto);
    }
}
