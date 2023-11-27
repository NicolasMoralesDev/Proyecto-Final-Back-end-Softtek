package TecnoTienda.tienda.dao;

import TecnoTienda.tienda.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IProductDao extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product  p WHERE p.category = :category")
    List<Product> findByCategory(@Param("category")String category);

}
