package pl.aeh.shop.service;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aeh.shop.domain.Cart;
import pl.aeh.shop.domain.Product;
import pl.aeh.shop.domain.cartLine.CartLine;
import pl.aeh.shop.domain.cartLine.CartLineKey;
import pl.aeh.shop.dto.TotalCartSize;
import pl.aeh.shop.exception.ServiceNoDataException;
import pl.aeh.shop.mapper.cartLine.CartLineMapper;
import pl.aeh.shop.mapper.cartLine.CartLineResponse;
import pl.aeh.shop.mapper.cartLine.CartLineResponseMapper;
import pl.aeh.shop.repository.CartLineRepository;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CartLineService {

  private final CartLineRepository cartLineRepository;
  private final CartLineResponseMapper cartLineResponseMapper;

  @Transactional
  public void saveCartLine(final CartLine cartLine) {
    cartLineRepository.save(cartLine);
  }

  @Transactional
  public void deleteCartLine(final CartLineKey cartLineKey) {
    var cartLine = cartLineRepository.findById(cartLineKey)
        .orElseThrow(() -> new ServiceNoDataException(String.format("Product with id: %d wasn't found", cartLineKey)));
    cartLineRepository.delete(cartLine);
  }

  public CartLineResponse getProductsFromCart(final long id) {
    return cartLineResponseMapper.getCartLine(cartLineRepository.findCartLineByCartId(id));
  }

  public CartLine getCartLine(final Cart cart, final Product product) {
    return cartLineRepository.findById(new CartLineKey(cart, product))
        .orElseThrow(() -> new ServiceNoDataException("CartLine wasn't found"));
  }

  public CartLine findCartLine(final Cart cart, final Product product) {
    return cartLineRepository.findById(new CartLineKey(cart, product))
        .orElseGet(() -> new CartLine(new CartLineKey(cart, product), 0, BigDecimal.valueOf(0.0)));
  }

  public List<CartLine> findCartLineByCartId(final long id) {
    return cartLineRepository.findCartLineByCartId(id);
  }

  @Transactional
  public void deleteCartLines(final Cart cart) {
    cartLineRepository.deleteAllInBatch(findCartLineByCartId(cart.getId()));
  }

  public TotalCartSize getTotalCostAndTotalQuantity(final Long id) {
    log.info("IN: method [getTotalCostAndTotalQuantity] - with cart id: {}", id);
    var totalCartSize = CartLineMapper.INSTANCE.mapTo(cartLineRepository.getTotalCostAndTotalQuantity(id));
    log.info("OUT: method [getTotalCostAndTotalQuantity] - with total cost = {} and total quantity = {}", totalCartSize.getTotalCost(),
        totalCartSize.getTotalQuantity());
    return totalCartSize;
  }

}
