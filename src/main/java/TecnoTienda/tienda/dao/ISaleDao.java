package TecnoTienda.tienda.dao;

import TecnoTienda.tienda.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISaleDao extends JpaRepository<Sale, Integer> {



    @Query("SELECT s FROM Sale s WHERE s.user.id = :userId")
    List<Sale> saleByUserId(@Param("userId") int userId);


}
