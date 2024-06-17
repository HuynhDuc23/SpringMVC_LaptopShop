package vn.com.demo.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.demo.domain.dto.Register;

@Controller
public class AuthController {
  @GetMapping("/auth/register")
  public String register(Model model) {
    Register register = new Register();
    model.addAttribute("register", register);
    return "client/auth/register";
  }

}
