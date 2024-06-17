package vn.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.demo.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
