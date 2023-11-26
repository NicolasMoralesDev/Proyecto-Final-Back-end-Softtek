package TecnoTienda.tienda.service;

import TecnoTienda.tienda.entity.Product;

import java.util.List;


public interface ProductService {

    public Product addProduct(Product product);

    public List<Product> getAllProducts();

    public Product findById(int id);
}
