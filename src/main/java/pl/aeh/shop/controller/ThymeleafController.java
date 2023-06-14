//package pl.aeh.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import pl.aeh.service.ProductService;
//
//@RequiredArgsConstructor
//@Controller
//public class ThymeleafController {
//
//  private final ProductService productService;
//
//  @GetMapping("/all")
//  public String getAllProducts(Model model) {
//    model.addAttribute("product", productService.findAll());
//    return "products";
//  }
//}
