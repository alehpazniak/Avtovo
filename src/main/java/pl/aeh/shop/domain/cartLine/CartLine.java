package pl.aeh.shop.domain.cartLine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Setter
@Entity
@Table
public class CartLine {

  @EmbeddedId
  @JsonIgnore
  private CartLineKey id;
  private int totalQuantity;
  private BigDecimal totalCost;

}
