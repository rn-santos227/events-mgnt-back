package com.mgnt.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {
  @Autowired
  private CsrfTokenRepository csrfTokenRepository;

    @GetMapping("/api/csrf-token")
  public CsrfToken getCsrfToken(HttpServletRequest request) {
    return csrfTokenRepository.generateToken(request);
  }
}
