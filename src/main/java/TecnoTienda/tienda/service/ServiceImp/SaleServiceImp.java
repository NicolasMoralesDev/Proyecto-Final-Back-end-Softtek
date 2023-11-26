package TecnoTienda.tienda.service.ServiceImp;


import TecnoTienda.tienda.dao.ISaleDao;
import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.entity.Sale;
import TecnoTienda.tienda.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImp implements SaleService {

    @Autowired
    ISaleDao orderDao;

    @Autowired
    IProductDao productDao;
    @Override
    public Sale saveSale(Sale sale){
        return orderDao.save(sale);
    }

}
