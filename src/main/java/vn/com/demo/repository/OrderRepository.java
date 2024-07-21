package vn.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.demo.domain.Order;
import vn.com.demo.domain.User;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  public List<Order> findByUser(User user);
}
