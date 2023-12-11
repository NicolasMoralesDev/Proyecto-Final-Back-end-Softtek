package TecnoTienda.tienda.mappers;

import TecnoTienda.tienda.dao.IProductDao;
import TecnoTienda.tienda.dto.CreateSaleRequestDTO;
import TecnoTienda.tienda.dto.SaleDTO;
import TecnoTienda.tienda.entity.Item;
import TecnoTienda.tienda.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SaleMapper {

    @Autowired
    IProductDao productDao;

    public SaleMapper() {
    }

    /**
     * This method maps a Sale entity to a SaleDTO
     * @param sale Sale entity to be mapped to a SaleDTO
     * @return SaleDTO
     */
    public SaleDTO saleToSaleDTO(Sale sale) {

        // Create a new SaleDTO
        SaleDTO saleDTO = new SaleDTO();

        // Set the attributes of the SaleDTO
        saleDTO.setId(sale.getId());
        saleDTO.setAddress(sale.getAddress());
        saleDTO.setPhone(sale.getPhone());
        saleDTO.setStatus(sale.getStatus());
        saleDTO.setDate(sale.getDate());

        // If the sale has items, map them to a new list of items
        if (sale.getItemList() != null) {

            // Create a new list of items in the SaleDTO
            for (Item item : sale.getItemList()) {
                Item i = new Item();
                i.setProduct(item.getProduct());
                i.setAmount(item.getAmount());

                // Add the item to the list of items in the SaleDTO
                saleDTO.getItemList().add(i);
            }
        }

        // Return the SaleDTO
        return saleDTO;
    }
    public Sale saleRequestDtoToSale(CreateSaleRequestDTO saleDto){
        
        Sale sale = new Sale();
        sale.setAddress(saleDto.getAddress());
        sale.setPhone(saleDto.getPhone());
        sale.setStatus(saleDto.getStatus());
        sale.setDate(LocalDate.now());
        for (Item item : saleDto.getItemList()) {
                Item i = new Item();
                i.setProduct(productDao.findById(item.getProduct().getId()).get());
                i.setAmount(item.getAmount());
                sale.getItemList().add(i);
        }
        return sale;
    }

    /**
     * This method maps a list of Sale entities to a list of SaleDTOs
     * @param listSale List of Sale entities to be mapped to a list of SaleDTOs
     * @return List of SaleDTOs
     */
    public List<SaleDTO> saleListToSaleDTOList(List<Sale> listSale){

        // Create a new list of SaleDTOs
        List<SaleDTO> listSaleDTO = null;

        // Map the list of Sale entities to a list of SaleDTOs
        listSaleDTO = listSale.stream()
                .map(this::saleToSaleDTO)
                .toList();

        // Return the list of SaleDTOs
        return listSaleDTO;
    }
}
