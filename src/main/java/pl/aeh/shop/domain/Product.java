package pl.aeh.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long productId;
  @NotEmpty(message = "Name should not be empty")
  @Size(min = 0, max = 30, message = "Brand should be between 2 and 30 characters")
  private String brand;
  @Size(min = 2, max = 40, message = "Number should be between 2 and 30 characters")
  private String partNumber;
//  @Size(min = 2, max = 50, message = "Category should be between 2 and 30 characters")
//  private String category;
  @Size(max = 1000, message = "Description should be max 100 characters")
  private String description;
  @Min(value = 0, message = "Quantity should be greater than 0")
  private int quantity;
  @Min(value = 0, message = "Cost should be greater than 0.00")
  private BigDecimal cost;
//  private transient BigDecimal unitTax;
  private double unitWeight;

//  private String countryOfOrigin;

}
