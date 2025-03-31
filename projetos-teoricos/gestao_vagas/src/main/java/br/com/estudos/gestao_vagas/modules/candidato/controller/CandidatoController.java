package br.com.estudos.gestao_vagas.modules.candidato.controller;

import br.com.estudos.gestao_vagas.modules.candidato.CandidatoEntity;
import br.com.estudos.gestao_vagas.modules.candidato.useCases.CriarCandidatoUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

  @Autowired
  private CriarCandidatoUseCase criarCandidatoUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidatoEntity candidato){
    try {
      var result =this.criarCandidatoUseCase.execute(candidato);
      return ResponseEntity.ok().body(result);
    }catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }
}
