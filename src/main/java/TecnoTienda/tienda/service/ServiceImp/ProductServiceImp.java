package TecnoTienda.tienda.service.ServiceImp;

import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.dto.ProductPaginationDTO;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.mappers.ProductMapper;
import TecnoTienda.tienda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    IProductDao productDao;
    @Autowired
    ProductMapper productMapper;
    @Override
    public ProductDTO addProduct(ProductDTO productDto) {
        try {
            Product product = productMapper.productDtoToProduct(productDto);
            return productMapper.productToProductDto(productDao.save(product));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductPaginationDTO findByCategory(String category, int page){
        
        Pageable pageable = PageRequest.of(page,10);
        
        //       crea el listado de productos paginable 
        ProductPaginationDTO listProducts = new ProductPaginationDTO();
        
//        se setean los datos devueltos por la bd y se modela un dto
        listProducts.setPage(page);
        listProducts.setProductos(productMapper.productListToProductDtoList(productDao.findByCategory(category, pageable).getContent()));
        listProducts.setTotal(productDao.findByCategory(category, pageable).getTotalPages());
        return listProducts;
    }
    @Override
    public ProductPaginationDTO getAllProducts(int page){
        
//        objeto pagina
        Pageable pageable = PageRequest.of(page,10);
//       crea el listado de productos paginable 
        ProductPaginationDTO listProducts = new ProductPaginationDTO();
        
//        se setean los datos devueltos por la bd y se modela un dto
        listProducts.setPage(page);
        listProducts.setProductos(productMapper.productListToProductDtoList(productDao.findAll(pageable).getContent() ));
        listProducts.setTotal(productDao.findAll(pageable).getTotalPages());
        return listProducts;
    }

    @Override
    public ProductDTO findById(int id){
        return  productMapper.productToProductDto(productDao.findById(id).get());
    }

    @Override
    public void softDeleteProductById(int id){
        productDao.softDeleteProductById(id);

    }
    @Override
    public void setActiveProductById(int id){
        productDao.setActiveProductById(id);
    }

    @Override
    public void addBulkProducts(List<ProductDTO> productsDto){
        productDao.saveAll(productMapper.productDtoListToProductList(productsDto));
    }
}
