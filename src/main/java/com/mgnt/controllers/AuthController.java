package com.mgnt.controllers;

import com.mgnt.models.User;
import com.mgnt.services.TokenService;
import com.mgnt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  private final UserService userService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
    this.userService = userService;
  }

  @PostMapping("/public/login")
  public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
    String email = loginRequest.get("email");
    String password = loginRequest.get("password");

    try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

    UserDetails userDetails = userService.loadUserByUsername(email);
    String token = tokenService.generateToken(((User) userDetails).getId());

    Map<String, String> response = new HashMap<>();
    response.put("token", token);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout() {
    return ResponseEntity.status(HttpStatus.OK).body("Logged out successfully.");
  }
}
