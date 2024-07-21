package vn.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.demo.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  public Cart findByUser(vn.com.demo.domain.User user);
}
