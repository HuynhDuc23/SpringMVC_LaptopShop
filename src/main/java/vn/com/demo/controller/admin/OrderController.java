package vn.com.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import vn.com.demo.domain.Order;
import vn.com.demo.domain.OrderDetail;
import vn.com.demo.domain.User;
import vn.com.demo.service.OrderService;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
  private OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/admin/order")
  public String findAllOrder(Model model) {
    List<Order> orders = this.orderService.findAllOrder();
    System.out.println(orders.toString() + " data");
    model.addAttribute("listorder", orders);
    return "admin/order/show";
  }

  @GetMapping("/admin/order/detail/{id}")
  public String detailWithOrderid(@PathVariable Long id, Model model) {
    List<OrderDetail> orderDetails = this.orderService.handlerFindOrderDetail(id);
    System.out.println(orderDetails.toString() + " data");
    model.addAttribute("orderDetails", orderDetails);
    return "admin/order/detail";
  }

  @GetMapping("/admin/order/delete/{id}")
  public String deleteOrder(@PathVariable Long id) {
    this.orderService.handlerOrder(id);
    return "redirect:/admin/order";
  }

  @GetMapping("/admin/order/update/{id}")
  public String updateOrder(@PathVariable Long id, Model model) {
    Order order = this.orderService.findOrderById(id);
    model.addAttribute("order", order);
    return "admin/order/update";
  }

  @PostMapping("/admin/order/update")
  public String postMethodName(@ModelAttribute("order") Order order) {
    this.orderService.updateOrder(order);
    return "redirect:/admin/order";
  }

  @GetMapping("/admin/order/history")
  public String historyOrder(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    User user = new User();
    user.setId((Long) session.getAttribute("id"));
    List<Order> orders = this.orderService.findOrderWithByUser(user, session);
    model.addAttribute("orders", orders);
    return "admin/order/order-history";
  }

}
