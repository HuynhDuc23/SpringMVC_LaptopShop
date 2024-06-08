package vn.com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.demo.domain.User;
import vn.com.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/list")
  public String getHomePage(Model model) {
    List<User> users = userService.getUsers();
    System.out.println(users);
    model.addAttribute("users", users);
    return "admin/user/ListUsers";
  }

  @GetMapping("/user-details/{id}")
  public String getUserDetailPage(Model model, @PathVariable("id") String id) {
    Long idLong = Long.parseLong(id);
    User user = userService.getUserById(idLong);
    model.addAttribute("user", user);
    return "admin/user/User-Details";
  }

  @GetMapping("/create")
  public String showCreateForm(Model model) {
    model.addAttribute("newUser", new User());
    return "admin/user/CreateFormUser";
  }

  @GetMapping("/update/{id}")
  public String getUser(@PathVariable("id") String id, Model model) {
    Long idLong = Long.parseLong(id);
    User user = userService.getUserById(idLong);
    model.addAttribute("newUser", user);
    return "admin/user/Update-User";
  }

  @PostMapping("/update/{id}")
  public String updateUserWithId(@PathVariable("id") String id, @ModelAttribute("newUser") User user) {
    this.userService.updateUser(user);
    return "redirect:/list";
  }

  @PostMapping("/createUser")
  public String processCreateUser(Model model, @ModelAttribute("newUser") User user) {
    userService.createUser(user);
    return "redirect:/list";
  }

  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") String id) {
    Long idLong = Long.parseLong(id);
    userService.deleteUserById(idLong);
    return "redirect:/list";
  }

}
