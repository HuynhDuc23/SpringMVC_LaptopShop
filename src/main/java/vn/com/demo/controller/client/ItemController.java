package vn.com.demo.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.com.demo.domain.Product;
import vn.com.demo.service.ProductService;

@Controller
public class ItemController {
  private final ProductService productService;

  public ItemController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/product/{id}")
  public String getProductPage(@PathVariable("id") Long id, Model model) {
    Product product = productService.findProductById(id);
    model.addAttribute("product", product);
    return "client/product/detail";
  }

}
