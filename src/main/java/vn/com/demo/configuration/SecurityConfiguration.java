package vn.com.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.DispatcherType;
import vn.com.demo.service.CustomerDetailsService;
import vn.com.demo.service.UserService;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

  private final String[] PUBLIC_ENDPOINT = { "/login", "/client/**", "/css/**", "/js/**",
      "/images/**", "/auth/register", "/product/**" };
  private final String[] PUBLIC_ENDPOINT_CLIENT = { "/", "/product/{id}" };

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(16);
  }

  @Bean
  public UserDetailsService userDetailsService(UserService userService) {
    return new CustomerDetailsService(userService);
  }

  // ghi đè UserDetailsService
  @Bean
  public DaoAuthenticationProvider authProvider(
      PasswordEncoder passwordEncoder,
      UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    authProvider.setHideUserNotFoundExceptions(false);
    return authProvider;
  }

  // ghi đè AuthenticationSuccessHandler
  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new CustomSucessHandler();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .dispatcherTypeMatchers(DispatcherType.FORWARD,
                DispatcherType.INCLUDE)
            .permitAll()
            .requestMatchers(PUBLIC_ENDPOINT)
            .permitAll()
            .requestMatchers(PUBLIC_ENDPOINT_CLIENT)
            .permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated())
        .sessionManagement((sessionManagement) -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .invalidSessionUrl("/logout?expired")
            .maximumSessions(1)
            .maxSessionsPreventsLogin(false))
        .logout(
            (logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true) // Invalidate session
                .deleteCookies("SESSION") // Delete cookie
                .permitAll())

        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .failureUrl("/login?error")
            .successHandler(authenticationSuccessHandler())
            .permitAll())
        .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"));
    ;

    return http.build();
  }
}
// fix errror : redirect many pages
// Tất cả đường link trên url phải khai báo hết
