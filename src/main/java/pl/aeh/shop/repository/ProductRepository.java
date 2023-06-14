package pl.aeh.shop.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.aeh.shop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findByBrand(String brand);

}
