package com.estudos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/segundaRota")
public class SegundaController {
  @GetMapping("/teste")
  public String primeiroMetodo(){
    return "Testando o Scan";
  }
}
