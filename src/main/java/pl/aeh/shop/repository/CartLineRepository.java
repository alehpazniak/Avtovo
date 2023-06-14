package pl.aeh.shop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.aeh.shop.domain.cartLine.CartLine;
import pl.aeh.shop.domain.cartLine.CartLineKey;

@Repository
public interface CartLineRepository extends JpaRepository<CartLine, CartLineKey> {

  @Query(value = "select cart_id, product_id, total_cost, total_quantity from cart_line cl "
      + "join cart c on cl.cart_id = c.id where cart_id = ?1",
      nativeQuery = true)
  List<CartLine> findCartLineByCartId(Long cartId);

  @Query(value =
      "select cl.cart_id as cart_id, sum(cl.product_id) as product_id, sum(cl.total_cost) as total_cost, sum(cl.total_quantity) as "
          + "total_quantity from "
          + "cart_line AS cl where cl.cart_id = ?1 group by cl.cart_id",
      nativeQuery = true)
  CartLine getTotalCostAndTotalQuantity(Long cartId);

}
