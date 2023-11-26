package TecnoTienda.tienda.controller;


import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }
    @PostMapping("/save")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
}
