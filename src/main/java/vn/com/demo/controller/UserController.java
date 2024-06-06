package vn.com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.com.demo.service.UserService;

@Controller
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String getHomePage(Model model) {
    String data = this.userService.getService();
    model.addAttribute("data", data);
    model.addAttribute("user", "TranVuHuynhDuc");
    return "hello";
  }
}
