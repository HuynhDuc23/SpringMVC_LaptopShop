package vn.com.demo.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // @Email(message = "Vui lòng nhập email chính xác")
  private String email;
  // @Size(min = 2, message = "Vui lòng nhập mật khẩu min = 2 and max = 20")
  private String password;
  // @NotNull(message = "Vui lòng nhập tên")
  private String fullName;
  // @NotNull(message = "Vui lòng nhập địa chỉ ")
  private String address;
  // @NotNull(message = "Vui lòng nhập số điện thoại")
  private String phone;
  private String avatar;
  @OneToMany(mappedBy = "user")
  List<Order> orders;
  // roleId
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

  @OneToOne(mappedBy = "user")
  private Cart Cart;

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public User() {
  }

  public User(Long id, String email, String password, String fullName, String address, String phone, String avatar) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.fullName = fullName;
    this.address = address;
    this.phone = phone;
    this.avatar = avatar;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", email=" + email + ", password=" + password + ", fullName=" + fullName + ", address="
        + address + ", phone=" + phone + ", avatar=" + avatar + "]";
  }

  public Cart getCart() {
    return Cart;
  }

  public void setCart(Cart cart) {
    Cart = cart;
  }

}
