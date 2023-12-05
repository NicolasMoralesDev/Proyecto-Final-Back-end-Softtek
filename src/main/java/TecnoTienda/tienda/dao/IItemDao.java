package TecnoTienda.tienda.dao;

import TecnoTienda.tienda.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemDao extends JpaRepository<Item,Integer> {

    @Query("SELECT i FROM Item i WHERE i.sale.id = :id")
    List<Item> getItemBySaleId(@Param("id") int id);
}
