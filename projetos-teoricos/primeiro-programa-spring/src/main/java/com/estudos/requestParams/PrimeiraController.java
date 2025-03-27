package com.estudos.requestParams;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/primeirarota")
public class PrimeiraController {
  @GetMapping("/primeirometodo/{id}") //path params
  public String primeiroMetodo(@PathVariable String id){
    return "O parâmetro é "+id;
  }
  @GetMapping("/queryparams")
  public String metodoQueryParamns(@RequestParam String id){
    return "o parametro é "+ id +" com queryParams";
  }
  @GetMapping("/queryparams2")
  public String metodoQueryParamnsMap(@RequestParam Map<String,String> allParamns){
    return "os parametros são :"+allParamns.entrySet();
  }
}
