package med.voll.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")

public class HelloControl {
 @GetMapping
 public String helloWorld() {
  return "Hello World Spring!";
 }
}
