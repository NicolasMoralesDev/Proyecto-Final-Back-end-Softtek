package TecnoTienda.tienda.service.ServiceImp;

import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.entity.Product;
import TecnoTienda.tienda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Product findByName(String name){
        return productDao.findByName(name);
    }
    @Override
    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    @Override
    public Product findById(int id){
        return productDao.findById(id).get();
    }
}
