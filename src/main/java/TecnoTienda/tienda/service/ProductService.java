package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.entity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    public Product addProduct(Product product);

    public ProductDTO getAllProducts();

    public Optional<Product> findById(int id);

    public List<Product> findByCategory(String category);

    public void softDeleteProductById(int id);

    public void setActiveProductById(int id);
}
