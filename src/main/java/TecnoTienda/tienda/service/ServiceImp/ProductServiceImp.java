package TecnoTienda.tienda.service.ServiceImp;

import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.dto.ProductDTO;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    IProductDao productDao;
    @Override
    public Product addProduct(Product product) {
        try {
            return productDao.save(product);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findByCategory(String category){
        return productDao.findByCategory(category);
    }
    @Override
    public Page <Product> getAllProducts(int page){
        
        Pageable pageable = PageRequest.of(page,10);
        return productDao.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(int id){
        return productDao.findById(id);
    }

    @Override
    public void softDeleteProductById(int id){
        productDao.softDeleteProductById(id);

    }
    @Override
    public void setActiveProductById(int id){
        productDao.setActiveProductById(id);
    }
}
