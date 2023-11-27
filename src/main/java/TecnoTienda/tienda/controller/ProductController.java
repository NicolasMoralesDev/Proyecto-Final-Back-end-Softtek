package TecnoTienda.tienda.controller;


import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
//Prueba git
    @Autowired
    ProductService productService;

    @GetMapping("/public/listproduct")
    public ResponseEntity<List<Product>> getAllProduct(){
        try{
            List<Product> productList = productService.getAllProducts();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @GetMapping("/public/productById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        try{
            Product product = productService.findById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(product);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/public/productByCategory/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category){
        try{
            List<Product> products = productService.findByCategory(category);
            return ResponseEntity.status(HttpStatus.FOUND).body(products);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/save/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        try{
            Product productSaved = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
