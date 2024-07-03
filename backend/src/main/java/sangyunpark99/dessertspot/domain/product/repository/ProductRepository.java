package sangyunpark99.dessertspot.domain.product.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sangyunpark99.dessertspot.domain.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN FETCH p.user WHERE p.id > :cursor ORDER BY p.id ASC")
    Optional<List<Product>> findByCursor(@Param("cursor") Long cursor, Pageable pageable);
}
