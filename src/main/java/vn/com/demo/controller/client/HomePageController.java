package vn.com.demo.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.demo.domain.Product;
import vn.com.demo.service.ProductService;
import java.util.List;

@Controller
public class HomePageController {

  private ProductService productService;

  public HomePageController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/")
  public String getMethodName(Model model) {
    List<Product> products = this.productService.listProducts();
    model.addAttribute("products", products);
    return "client/homepage/show";
  }

}
