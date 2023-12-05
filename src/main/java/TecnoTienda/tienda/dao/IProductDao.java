package TecnoTienda.tienda.dao;

import TecnoTienda.tienda.entity.Product;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IProductDao extends JpaRepository<Product,Integer> {


    @Query("SELECT p FROM Product  p WHERE p.category = :category AND p.status = 'on'")
    List<Product> findByCategory(@Param("category")String category);



    @Override
    @NonNull
    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.status = 'on'")
    Optional<Product> findById(@Param("id") Integer id);

    @Override
    @NonNull
    @Query("SELECT p FROM Product p WHERE p.status = 'on'")
    List<Product> findAll();


    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.status = 'off' WHERE p.id = :id")
    void softDeleteProductById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.status = 'on' WHERE p.id = :id")
    void setActiveProductById(@Param("id") int id);
}
