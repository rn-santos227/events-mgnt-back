package com.mgnt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class SecurityConfig {
  @Bean
  public CsrfTokenRepository csrfTokenRepository() {
    return new CookieCsrfTokenRepository();
  }
}
