package pl.aeh.shop.mapper.cartLine;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import pl.aeh.shop.domain.Product;
import pl.aeh.shop.domain.cartLine.CartLine;

@Component
public class CartLineResponseMapper {

  public CartLineResponse getCartLine(final List<CartLine> cartLines) {
    var cartLineResponse = new CartLineResponse();
    cartLineResponse.setTotalCost(getTotalCost(cartLines));
    cartLineResponse.setTotalQuantity(getTotalQuantity(cartLines));
    cartLineResponse.setProducts(getProducts(cartLines));
    return cartLineResponse;
  }

  private int getTotalQuantity(final List<CartLine> cartLines) {
    return cartLines.stream()
        .map(CartLine::getTotalQuantity)
        .reduce(Integer::sum).orElse(0);
  }

  private BigDecimal getTotalCost(final List<CartLine> cartLines) {
    return cartLines.stream()
        .map(CartLine::getTotalCost)
        .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
  }

  private List<Product> getProducts(final List<CartLine> cartLines) {
    return cartLines.stream()
        .map(this::getProduct)
        .collect(Collectors.toList());
  }

  private Product getProduct(final CartLine cartLine) {
    return Product.builder()
        .brand(cartLine.getId().getProduct().getBrand())
//        .category(cartLine.getId().getProduct().getCategory())
        .cost(cartLine.getTotalCost())
        .description(cartLine.getId().getProduct().getDescription())
        .partNumber(cartLine.getId().getProduct().getPartNumber())
        .quantity(cartLine.getTotalQuantity())
        .build();
  }

}
