package pl.aeh.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aeh.shop.domain.Cart;
import pl.aeh.shop.domain.Product;
import pl.aeh.shop.dto.TotalCartSize;
import pl.aeh.shop.mapper.cartLine.CartLineResponse;
import pl.aeh.shop.service.CartService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cart")
public class CartController {

  private final CartService cartService;

  @GetMapping("/{id}")
  public CartLineResponse getProductsFromCart(@PathVariable(value = "id") int id) {
    log.info("IN: method [getProductsFromCart] - cart with id: {}", id);
    return cartService.getProductsFromCart(id);
  }


  @PostMapping("/add/{id}")
  public TotalCartSize addProductsToCart(
      @PathVariable(value = "id") long id, @RequestBody Product product
  ) {
    log.info("IN: method [addProductsToCart] - cart with id: {} & product: id={}, quantity={}, cost={}", id, product.getProductId(),
        product.getQuantity(), product.getCost());
    return cartService.addProductsToCart(id, product);
  }

  @PostMapping("/add")
  public Cart addCart(@RequestBody Cart cart) {
    return cartService.saveCart(cart);
  }

  @PutMapping("/change/{id}")
  public TotalCartSize changeQuantityOfProducts(@PathVariable(value = "id") long id, @RequestBody Product product) {
    log.info("IN: method [changeNumberOfProducts] - cart with id: {} & product: id={}, quantity={}", id, product.getProductId(),
        product.getQuantity());
    return cartService.changeQuantity(id, product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> clearCart(@PathVariable(value = "id") long id) {
    return cartService.clearCart(id);
  }
}
