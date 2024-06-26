package vn.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public User findByFullName(String name);

  public User findByEmail(String email);
}
