package pl.aeh.shop.domain.cartLine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.aeh.shop.domain.Cart;
import pl.aeh.shop.domain.Product;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class CartLineKey implements Serializable {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id")
  @JsonIgnoreProperties(ignoreUnknown = true)
  private Cart cart;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  @JsonIgnoreProperties(ignoreUnknown = true)
  private Product product;

}
