package pl.aeh.shop.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.experimental.UtilityClass;
import pl.aeh.shop.domain.Product;
import pl.aeh.shop.domain.cartLine.CartLine;

@UtilityClass
public class Utils {

  public static BigDecimal getTotalCostOfCart(final BigDecimal cartCost, final Product product) {
    return product.getCost().multiply(BigDecimal.valueOf(product.getQuantity())).add(cartCost);
  }

  public static BigDecimal getTotalCostOfCart(final BigDecimal cartCost, final BigDecimal cartLineTotalCost, final BigDecimal carLineCost) {
    return cartCost.subtract(cartLineTotalCost.subtract(carLineCost));
  }

  public static BigDecimal getTotalCostOfCartLine(final CartLine cartLine, final int quantity) {
    return cartLine.getTotalCost().divide(BigDecimal.valueOf(cartLine.getTotalQuantity()), 2, RoundingMode.HALF_UP)
        .multiply(BigDecimal.valueOf(quantity));
  }

}
