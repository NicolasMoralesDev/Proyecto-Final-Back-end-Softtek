package TecnoTienda.tienda.mappers;

import TecnoTienda.tienda.dto.SaleDTO;
import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Sale;
import org.springframework.stereotype.Service;

@Service
public class SaleRowMapper {

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
                i.setProduct(item.getProduct());
                i.setAmount(item.getAmount());
                saleDTO.getItemList().add(i);
            }
        }
        return saleDTO;
    }
}
