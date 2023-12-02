package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.entity.Product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;


public interface ProductService {

    public Product addProduct(Product product);

    public Page <Product> getAllProducts(int page);

    public Optional<Product> findById(int id);

    public List<Product> findByCategory(String category);

    public void softDeleteProductById(int id);

    public void setActiveProductById(int id);
}
