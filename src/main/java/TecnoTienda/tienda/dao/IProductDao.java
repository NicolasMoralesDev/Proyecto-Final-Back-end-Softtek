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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface IProductDao extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product  p WHERE p.category = :category AND p.status = 'on' AND p.stock > 0")
    Page<Product> findByCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:search% AND p.status = 'on' AND p.stock > 0")
    Page<Product> findProductsBySearchQuery(@Param("search") String search, Pageable pageable);

    @Override
    @NonNull
    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.status = 'on' AND p.stock > 0")
    Optional<Product> findById(@Param("id") @NonNull Integer id);


    @NonNull
    @Query("SELECT p FROM Product p WHERE p.status = 'on' AND p.stock > 0")
    Page<Product> findAllPage(@Param("pageable") Pageable pageable);
    List<Product> findAll();


    @Query("UPDATE Product p SET p.stock = :stock WHERE p.id = :id")
    void setStockById(@Param("id") int id, @Param("stock") int stock);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.status = 'off' WHERE p.id = :id")
    void softDeleteProductById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.status = 'on' WHERE p.id = :id")
    void setActiveProductById(@Param("id") int id);
}
