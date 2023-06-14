package pl.aeh.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aeh.shop.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
