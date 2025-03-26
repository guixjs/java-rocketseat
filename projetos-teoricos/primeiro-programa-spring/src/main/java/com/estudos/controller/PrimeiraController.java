package com.estudos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeirarota")
public class PrimeiraController {
  @GetMapping("/primeirometodo/{id}")
  public String primeiroMetodo(@PathVariable String id){
    return "O parâmetro é "+id;
  }
}
