package vn.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.demo.domain.CartDetail;
import vn.com.demo.domain.Product;
import vn.com.demo.domain.Cart;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
  public boolean existsByCartAndProduct(Cart cart, Product product);

  public CartDetail findByCartAndProduct(Cart cart, Product product);
}