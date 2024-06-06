package vn.com.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  public static final String[] ENDPOINTS = { "/**" };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(c -> c.disable());
    httpSecurity.authorizeHttpRequests(
        request -> request.requestMatchers(HttpMethod.GET, ENDPOINTS)
            .permitAll());
    return httpSecurity.build();

  }
}
