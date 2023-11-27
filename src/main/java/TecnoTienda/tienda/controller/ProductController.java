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

    @GetMapping("/public/product/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable("name") String name){
        try{
            Product product = productService.findByName(name);
            return ResponseEntity.status(HttpStatus.FOUND).body(product);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/admin/save")
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
