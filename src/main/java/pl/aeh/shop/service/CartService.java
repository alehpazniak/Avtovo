package pl.aeh.shop.service;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aeh.shop.domain.Cart;
import pl.aeh.shop.domain.Product;
import pl.aeh.shop.domain.cartLine.CartLine;
import pl.aeh.shop.dto.TotalCartSize;
import pl.aeh.shop.exception.ServiceNoDataException;
import pl.aeh.shop.mapper.cartLine.CartLineResponse;
import pl.aeh.shop.repository.CartRepository;
import pl.aeh.shop.repository.ProductRepository;
import pl.aeh.shop.util.Utils;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CartService {

  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final CartLineService cartLineService;

  @Transactional
  public TotalCartSize addProductsToCart(long cartId, Product productRequest) {
    var cart = cartRepository.findById(cartId)
        .orElseThrow(() -> new ServiceNoDataException(String.format("Cart with id: %d wasn't found", cartId)));
    var product = productRepository.findById(productRequest.getProductId())
        .orElseThrow(() -> new ServiceNoDataException(String.format("Product with id: %d wasn't found", productRequest.getProductId())));
    var cartLine = cartLineService.findCartLine(cart, product);
    cartLineService.saveCartLine(getCartLine(cartLine, productRequest));
    return getCartLineResponse(cartLine);
  }

  private TotalCartSize getCartLineResponse(final CartLine cartLine) {
    return cartLineService.getTotalCostAndTotalQuantity(cartLine.getId().getCart().getId());
  }

  @Transactional
  public TotalCartSize changeQuantity(final long cartId, final Product productRequest) {
    var cart = cartRepository.findById(cartId)
        .orElseThrow(() -> new ServiceNoDataException(String.format("Cart with id: %d wasn't found", cartId)));
    var product = productRepository.findById(productRequest.getProductId())
        .orElseThrow(() -> new ServiceNoDataException(String.format("Product with id: %d wasn't found", productRequest.getProductId())));
    var cartLine = cartLineService.getCartLine(cart, product);
    if (productRequest.getQuantity() == 0) {
      cartLineService.deleteCartLine(cartLine.getId());
    } else if (productRequest.getQuantity() != cartLine.getTotalQuantity()) {
      var carLineCost = Utils.getTotalCostOfCartLine(cartLine, productRequest.getQuantity());
      cartLineService.saveCartLine(getNewCartLineData(cartLine, carLineCost, productRequest.getQuantity()));
    }
    return getCartLineResponse(cartLine);
  }

  private CartLine getNewCartLineData(final CartLine cartLine, final BigDecimal carLineCost, final int quantity) {
    cartLine.setTotalCost(carLineCost);
    cartLine.setTotalQuantity(quantity);
    return cartLine;
  }

  @Transactional
  public ResponseEntity<Object> clearCart(final long cartId) {
    cartLineService.deleteCartLines(cartRepository.findById(cartId)
        .orElseThrow(() -> new ServiceNoDataException(String.format("Cart with id: %d wasn't found", cartId))));
    return ResponseEntity.ok().build();
  }

  public Cart saveCart(final Cart cart) {
    return cartRepository.save(cart);
  }

  public CartLineResponse getProductsFromCart(final long cartId) {
    cartRepository.findById(cartId)
        .orElseThrow(() -> new ServiceNoDataException(String.format("Cart with id: %d wasn't found", cartId)));
    return cartLineService.getProductsFromCart(cartId);
  }

  private CartLine getCartLine(final CartLine cartLine, final Product productRequest) {
    return CartLine.builder()
        .id(cartLine.getId())
        .totalQuantity(cartLine.getTotalQuantity() + productRequest.getQuantity())
        .totalCost(Utils.getTotalCostOfCart(cartLine.getTotalCost(), productRequest))
        .build();
  }

}




