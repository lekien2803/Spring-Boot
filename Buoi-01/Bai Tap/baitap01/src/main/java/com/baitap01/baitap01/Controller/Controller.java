package com.baitap01.baitap01.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {
  @GetMapping
  @ResponseBody
  public String helloWorld() {
    return "hello world";
  }

  @GetMapping("/add/{a}/{b}")
  @ResponseBody
  public int add(@PathVariable("b") int b, @PathVariable("a") int a) {
    return a + b;
  }
}
