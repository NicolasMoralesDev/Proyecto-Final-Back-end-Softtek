package TecnoTienda.tienda.dao;

import TecnoTienda.tienda.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemDao extends JpaRepository<Item,Integer> {
}
