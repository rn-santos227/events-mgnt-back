package com.mgnt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @GetMapping("/public/test")
  public String test() {
    return "Hello, this is a test route!";
  }
}
