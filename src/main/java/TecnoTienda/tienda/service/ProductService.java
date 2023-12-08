package TecnoTienda.tienda.service;

import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.dto.ProductPaginationDTO;
import TecnoTienda.tienda.entity.Product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;


public interface ProductService {

    public ProductDTO addProduct(ProductDTO productDto);

    public ProductPaginationDTO getAllProducts(int page);
    
    public ProductPaginationDTO getProductByQuery(String q, int page);

    public ProductDTO findById(int id);

    public ProductPaginationDTO findByCategory(String category, int page);

    public void softDeleteProductById(int id);

    public void setStockById(int id,int stock);
    public void setActiveProductById(int id);

    public void addBulkProducts(List<ProductDTO> products);
}
