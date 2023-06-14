package pl.aeh.shop.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.aeh.shop.domain.Product;
import pl.aeh.shop.exception.ServiceNoDataException;
import pl.aeh.shop.repository.ProductRepository;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductService {

  private final ProductRepository productRepository;

  @Transactional
  public Product saveProduct(final Product product) {
    return productRepository.save(product);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public List<Product> findProductsByBrand(final String brand) {
    return productRepository.findByBrand(brand).stream().sorted(Comparator.comparing(Product::getCost).reversed())
        .collect(Collectors.toList());
  }

  public Product findById(final Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ServiceNoDataException(String.format("Product with id: %d wasn't found", id)));
  }

  @Transactional
  public void deleteProduct(final Long id) {
    productRepository.deleteById(id);
  }

  @Transactional
  public Product updateProduct(final Long id, final Product updatedProduct) {
    var product = productRepository.findById(id)
        .orElseThrow(() -> new ServiceNoDataException(String.format("Product with id: %d wasn't found", id)));
    product.setBrand(updatedProduct.getBrand());
    product.setPartNumber(updatedProduct.getPartNumber());
//    product.setCategory(updatedProduct.getCategory());
    product.setDescription(updatedProduct.getDescription());
    product.setQuantity(updatedProduct.getQuantity());
    product.setCost(updatedProduct.getCost());
    productRepository.save(product);
    return product;
  }

}
