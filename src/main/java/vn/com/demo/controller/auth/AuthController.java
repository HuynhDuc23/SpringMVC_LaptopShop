package vn.com.demo.controller.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.com.demo.domain.User;
import vn.com.demo.domain.dto.Login;
import vn.com.demo.domain.dto.Register;
import vn.com.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/auth/register")
  public String register(Model model) {
    Register register = new Register();
    model.addAttribute("register", register);
    return "client/auth/register";
  }

  @GetMapping("/login")
  public String login(Model model) {
    Login newUser = new Login();
    model.addAttribute("newUser", newUser);
    return "client/auth/login";
  }

  @GetMapping("/logout")
  public String logout(Model model) {
    Login newUser = new Login();
    model.addAttribute("newUser", newUser);
    return "client/auth/login";
  }

  @PostMapping("/auth/register-account")
  public String registerAccount(@ModelAttribute("register") Register register, Model model) {
    if (register.getEmail() == null || register.getFirstName() == null || register.getPassword() == null
        || register.getPassword() == null || register.getConfirmPassword() == null || register.getLastName() == null) {
      return "/auth/register";
    }
    User user = new User();
    user = userService.getUserByEmail(register.getEmail());
    if (user != null) {
      return "/auth/register";
    }
    user = userService.getUserByFullName(register.getLastName() + register.getFirstName());
    if (user != null) {
      return "/auth/register";
    }
    if (!register.getPassword().equals(register.getConfirmPassword())) {
      return "/auth/register";
    }
    String passEncode = this.passwordEncoder.encode(register.getPassword().toString());
    register.setPassword(passEncode);
    User userResult = userService.RegisterToUser(register);
    userService.createUser(userResult);

    User newUser = new User();
    newUser.setEmail(userResult.getEmail().toString());
    model.addAttribute("newUser", newUser);
    return "client/auth/login";
  }

}
