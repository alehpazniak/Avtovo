package pl.aeh.shop.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.aeh.shop.domain.Product;
import pl.aeh.shop.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/brand")
  public List<Product> getProductsByBrand(@RequestParam String brand) {
    return productService.findProductsByBrand(brand);
  }

  @GetMapping("{id}")
  public Product getById(@PathVariable long id) {
    return productService.findById(id);
  }

//  @GetMapping
//  public String getAllProducts(Model model) {
//    model.addAttribute("products", productService.findAll());
//    return "index";
//  }
//
//  @GetMapping("/{id}")
//  public String getProduct(@PathVariable("id") Long id, Model model) {
//    model.addAttribute("product", productService.findById(id));
//    return "index";
//  }
//
//  @GetMapping("addproduct")
//  public String showAddForm(@ModelAttribute("product") Product product) {
//    return "add-product";
//  }
//
//  @PostMapping("/addproduct")
//  public String createProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
//    if (bindingResult.hasErrors()) {
//      return "add-product";
//    }
//    productService.saveProduct(product);
//    return "redirect:/product";
//  }
//
//  @GetMapping("/edit/{id}")
//  public String showUpdateForm(Model model, @PathVariable("id") Long id) {
//    model.addAttribute("product", productService.findById(id));
//    return "update-product";
//  }
//
//  @PostMapping("/update/{id}")
//  public String updateProduct(
//      @PathVariable final Long id, @ModelAttribute("product") @Valid Product product, BindingResult bindingResult, Model model
//  ) {
//    if (bindingResult.hasErrors()) {
//      return "update-product";
//    }
//    productService.updateProduct(id, product);
//    return "redirect:/product";
//  }
//
//  @GetMapping("/delete/{id}")
//  public String deleteProduct(@PathVariable("id") Long id) {
//    productService.deleteProduct(id);
//    return "redirect:/product";
//  }
//
//  @GetMapping
//  public String addProduct(@ModelAttribute("product") Product product) {
//    return "product/new";
//  }

}
