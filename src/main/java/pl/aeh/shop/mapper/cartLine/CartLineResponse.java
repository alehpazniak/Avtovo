package pl.aeh.shop.mapper.cartLine;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import pl.aeh.shop.domain.Product;

@Data
public class CartLineResponse {

  private int totalQuantity;
  private BigDecimal totalCost;
  private List<Product> products;

}
