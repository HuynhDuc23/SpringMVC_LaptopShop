package vn.com.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;

import vn.com.demo.domain.Role;
import vn.com.demo.domain.User;
import vn.com.demo.domain.dto.Register;
import vn.com.demo.repository.OrderRepository;
import vn.com.demo.repository.ProductRepository;
import vn.com.demo.repository.RoleRepository;
import vn.com.demo.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;

  public UserService(UserRepository userRepository, RoleRepository roleRepository, ProductRepository productRepository,
      OrderRepository orderRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
  }

  public User createUser(User user) {
    return this.userRepository.save(user);
  }

  public List<User> getUsers() {
    return this.userRepository.findAll();
  }

  public User getUserById(Long id) {
    return this.userRepository.findById(id).get();
  }

  public User updateUser(User user) {
    User userData = new User();
    userData.setId(user.getId());
    userData.setEmail(user.getEmail());
    userData.setPassword(userData.getPassword());
    userData.setFullName(user.getFullName());
    userData.setAddress(user.getAddress());
    userData.setPhone(user.getPhone());
    return this.userRepository.save(userData);
  }

  public void deleteUserById(Long id) {
    this.userRepository.deleteById(id);
  }

  public Role getRoleByName(String name) {
    return this.roleRepository.findByName(name);
  }

  public User getUserByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  public User getUserByFullName(String name) {
    return this.userRepository.findByFullName(name);
  }

  public User RegisterToUser(Register register) {
    User user = new User();
    user.setFullName(register.getLastName() + register.getFirstName());
    user.setPassword(register.getPassword());
    user.setEmail(register.getEmail());
    user.setRole(roleRepository.findByName("USER"));
    return user;
  }

  public long countUsers() {
    return this.userRepository.count();
  }

  public long countProducts() {
    return this.productRepository.count();
  }

  public long countOrders() {
    return this.orderRepository.count();
  }
}
