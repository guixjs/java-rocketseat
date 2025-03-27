package br.com.estudos.gestao_vagas.modules.candidato.controller;

import br.com.estudos.gestao_vagas.modules.candidato.CandidatoEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

  @PostMapping("/")
  public void create(@RequestBody CandidatoEntity candidato){
    System.out.println("candidato");
    System.out.println(candidato.getName());
  }
}
