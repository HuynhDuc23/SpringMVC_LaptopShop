package vn.com.demo.controller.client;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.com.demo.domain.Cart;
import vn.com.demo.domain.CartDetail;
import vn.com.demo.domain.Product;
import vn.com.demo.domain.User;
import vn.com.demo.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

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
    model.addAttribute("id", id);
    return "client/product/detail";
  }

  @PostMapping("/add-product-to-cart/{id}")
  public String addProductToCart(@PathVariable("id") Long id, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    String email = (String) session.getAttribute("email");
    long product_id = id;
    this.productService.handleAddProductToCart(email, id, session);
    return "redirect:/";
  }

  @GetMapping("/cart")
  public String cartProduct(Model model, HttpServletRequest request) {
    User currentUser = new User();
    HttpSession session = request.getSession(false);
    currentUser.setId((Long) session.getAttribute("id"));

    Cart cart = this.productService.fetchByUser(currentUser);

    List<CartDetail> cardDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCart_detail();

    double totalPrice = 0;
    for (var item : cardDetails) {
      totalPrice += item.getPrice() * item.getQuantity();
    }
    model.addAttribute("cartDetails", cardDetails);
    model.addAttribute("totalPrice", totalPrice);
    model.addAttribute("cart", cart);
    return "client/cart/show";
  }

  @PostMapping("/delete-cart-detail-id/{id}")
  public String deleteCartDetail(@PathVariable Long id, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    this.productService.handleRenoveCartDetail(id, session);
    return "redirect:/cart";
  }

  @GetMapping("/checkout")
  public String getCheckOutPage(Model model, HttpServletRequest request) {
    User currentUser = new User();// null
    HttpSession session = request.getSession(false);
    long id = (long) session.getAttribute("id");
    currentUser.setId(id);

    Cart cart = this.productService.fetchByUser(currentUser);

    List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCart_detail();

    double totalPrice = 0;
    for (CartDetail cd : cartDetails) {
      totalPrice += cd.getPrice() * cd.getQuantity();
    }

    model.addAttribute("cartDetails", cartDetails);
    model.addAttribute("totalPrice", totalPrice);

    return "client/cart/checkout";
  }

  @PostMapping("/confirm-checkout")
  public String getCheckOutPage(@ModelAttribute("cart") Cart cart) {
    List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCart_detail();
    this.productService.handleUpdateCartBeforeCheckout(cartDetails);
    return "redirect:/checkout";
  }

  @PostMapping("/place-order")
  public String handlePlaceOrder(
      HttpServletRequest request,
      @RequestParam("receiverName") String receiverName,
      @RequestParam("receiverAddress") String receiverAddress,
      @RequestParam("receiverPhone") String receiverPhone) {
    HttpSession session = request.getSession(false);
    User currentUser = new User();
    currentUser.setId((Long) session.getAttribute("id"));
    this.productService.handlePlaceOrder(session, currentUser, receiverName, receiverAddress, receiverPhone);

    return "redirect:/";
  }
}
