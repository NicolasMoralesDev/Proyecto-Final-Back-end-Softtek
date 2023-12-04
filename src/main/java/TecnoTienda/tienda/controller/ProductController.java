package TecnoTienda.tienda.controller;


import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    
    @Autowired
    ProductService productService;


    // ----   METODOS PUBLICOS
    @Operation(summary = "Endpoint publico, Traer Todos los Productos")
    @GetMapping("/public/products")
    public ResponseEntity<?> getAllProduct(@RequestParam int page){
        try{
           
            ProductDTO listProducts = new ProductDTO();
            listProducts.setPage(productService.getAllProducts(page).getPageable().getPageNumber());
            listProducts.setProductos(productService.getAllProducts(page).getContent());
            listProducts.setTotal(productService.getAllProducts(page).getTotalPages());

           return ResponseEntity.status(HttpStatus.ACCEPTED).body(listProducts);
           
        }catch (Exception e){
            
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            
        }
    }

    @Operation(summary = "Endpoint de acceso Rol publico, Traer producto por Id")
    @GetMapping("/public/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        try{
            Product product = productService.findById(id).get();
            return ResponseEntity.status(HttpStatus.FOUND).body(product);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @Operation(summary = "Endpoint de acceso Rol publico, Busca Productos por id y los filtra por categoria")
    @GetMapping("/public/products/categories/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category){
        try{
            List<Product> products = productService.findByCategory(category);
            return ResponseEntity.status(HttpStatus.FOUND).body(products);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, Desactiva un producto que este Activado")
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") int id){
        try {
            productService.softDeleteProductById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, Activa un producto que este desactivado")
    @PutMapping ("/admin/ActiveProduct/{id}")
    public ResponseEntity<?> activeProductById(@PathVariable("id") int id){
        try {
            productService.setActiveProductById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Endpoint solo accesible con rol de Admin, guardar un producto en la base de datos")
    @PostMapping("/admin/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        try{
            Product productSaved = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
