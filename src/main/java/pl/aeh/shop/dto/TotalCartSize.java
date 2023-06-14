package pl.aeh.shop.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TotalCartSize {

  private Integer totalQuantity;
  private BigDecimal totalCost;

}
