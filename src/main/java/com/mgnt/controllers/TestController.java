package com.mgnt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
  @GetMapping("/public/test")
  public String test() {
    return "Hello, this is a test route!";
  }

  @PostMapping("/public/test")
  public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
    String email = loginRequest.get("email");
    String password = loginRequest.get("password");

    Map<String, String> response = new HashMap<>();
    response.put("user", email);
    response.put("password", password);

    return ResponseEntity.ok(response);
  }
}
