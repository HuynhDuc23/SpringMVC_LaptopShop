package vn.com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.demo.domain.User;
import vn.com.demo.repository.UserRepository;

@Service
public class UserService {
  public UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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

}
