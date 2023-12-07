package TecnoTienda.tienda.controller;

import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.dto.ProductPaginationDTO;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.mappers.ProductMapper;
import TecnoTienda.tienda.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productMapper;

    // ----   METODOS PUBLICOS
    @Operation(summary = "Endpoint de acceso Rol publico, Traer producto por Id")
    @GetMapping("/public/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(productService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint publico, Traer Todos los Productos que contengan la query")
    @GetMapping("/public/product")
    public ResponseEntity<?> getProductByQuery(@RequestParam String q, @RequestParam int page) {
        try {
////
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.getProductByQuery(q, page));
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
    }

    @Operation(summary = "Endpoint publico, Traer Todos los Productos")
    @GetMapping("/public/products")
    public ResponseEntity<?> getAllProduct(@RequestParam int page) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.getAllProducts(page));
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }
    }

    @Operation(summary = "Endpoint de acceso Rol publico, Busca Productos por id y los filtra por categoria")
    @GetMapping("/public/products/categories/{category}")
    public ResponseEntity<?> getProductByCategory(@PathVariable("category") String category,
            @RequestParam int page) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.findByCategory(category, page));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, Desactiva un producto que este Activado")
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") int id) {
        try {
            productService.softDeleteProductById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, Activa un producto que este desactivado")
    @PutMapping("/admin/ActiveProduct/{id}")
    public ResponseEntity<?> activeProductById(@PathVariable("id") int id) {
        try {
            productService.setActiveProductById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, guardar un producto en la base de datos")
    @PostMapping("/admin/products")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, Agrega un bulk de productos a la base de datos")
    @PostMapping("/admin/products/bulk")
    public ResponseEntity<?> addBulkProduct(@RequestBody List<ProductDTO> productsDto) {
        try {
            productService.addBulkProducts(productsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Productos agregados correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
