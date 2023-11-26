package TecnoTienda.tienda.dao;

import TecnoTienda.tienda.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleDao extends JpaRepository<Sale, Integer> {


}
