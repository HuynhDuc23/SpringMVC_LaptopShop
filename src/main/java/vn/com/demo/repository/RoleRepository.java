package vn.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.demo.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  public Role findByName(String name);
}
