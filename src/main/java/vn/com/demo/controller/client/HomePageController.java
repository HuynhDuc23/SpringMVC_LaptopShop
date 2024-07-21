package vn.com.demo.controller.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    Pageable pageable = PageRequest.of(0, 10);
    Page<Product> products = this.productService.listProducts(pageable);
    List<Product> listProducts = products.getContent();
    model.addAttribute("products", listProducts);
    return "client/homepage/show";
  }

  @GetMapping("/access-denied")
  public String accessDenied() {
    return "client/homepage/deny";
  }

}
