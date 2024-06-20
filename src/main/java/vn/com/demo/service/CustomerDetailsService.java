package vn.com.demo.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {
  private UserService userService;

  public CustomerDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    vn.com.demo.domain.User user = this.userService.getUserByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException(username + " does not exist");
    }
    return new User(user.getEmail(), user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())));
  }
}
// mac dinh spring security nó sẽ lưu user và password inmemory -> cần ghi đè
// lại để kiểm tra user và password từ db
// UserDetails là một interface có User của spring security implementation , chỉ
// cần tạo nó ra thì nó sẽ tự ép kiểm
// user : spring (username , password , true , true , true , true , authorities)
// trong spring cũng có 1 class User và hiện tại mình cũng đang định nghĩa User
// cách để phân biệt User của spring security và User do người dùng định nghĩa
// username -> từ cái form mình nhập
// Cần nói cho spring hiểu muốn dùng class CustomerDetailService thay vì mặc
// định UserDetailService

// ghi de userDEtailService
// AuthenticationManager : có nhiệm vụ xâu chuỗi các sự kiện lại với nhau , nó
// hoạt động như nào , nap để chạy dự án
// spring contextholder : không lưu password của người dùng -> do javaspring nó
// lưu
// cấu hình lại login của bản thân
// hasRole : tu dong bo ROLE_