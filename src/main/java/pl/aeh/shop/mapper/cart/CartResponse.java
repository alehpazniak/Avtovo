package pl.aeh.shop.mapper.cart;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class CartResponse {

  private Long id;
  private BigDecimal cost;
  private List<ProductResponse> products;

  @Data
  public static class ProductResponse {

    private String brand;
    private String number;
    private String category;
    private String description;
    private int quantity;
    private BigDecimal cost;

  }


}
