package TecnoTienda.tienda.mappers;

import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.dto.CreateSaleRequestDTO;
import TecnoTienda.tienda.dto.SaleDTO;
import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Sale;
import TecnoTienda.tienda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Component
public class SaleRowMapper {

    @Autowired
    IProductDao productDao;

    public SaleRowMapper() {
    }

    public SaleDTO saleToSaleDTO(Sale sale) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(sale.getId());
        saleDTO.setAddress(sale.getAddress());
        saleDTO.setPhone(sale.getPhone());
        saleDTO.setDate(sale.getDate());
        if (sale.getItemList() != null) {
            for (Item item : sale.getItemList()) {
                Item i = new Item();
                i.setId(item.getId());
                // TODO: Product mapper?
                i.setProduct(item.getProduct());
                i.setAmount(item.getAmount());
                saleDTO.getItemList().add(i);
            }
        }
        return saleDTO;
    }
    public Sale saleRequestDtoToSale(CreateSaleRequestDTO saleDto){
        Sale sale = new Sale();
        sale.setAddress(saleDto.getAddress());
        sale.setPhone(saleDto.getPhone());
        sale.setDate(LocalDate.now());
        for (Item item : saleDto.getItemList()) {
                Item i = new Item();
                // TODO: Product mapper?
                i.setProduct(productDao.findById(item.getProduct().getId).get());
                i.setAmount(item.getAmount());
                i.setSale(sale);
                sale.getItemList().add(i);
        }
        return sale;
    }
}
