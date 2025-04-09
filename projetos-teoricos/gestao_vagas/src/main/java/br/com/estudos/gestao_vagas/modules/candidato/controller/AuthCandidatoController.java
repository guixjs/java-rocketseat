package br.com.estudos.gestao_vagas.modules.candidato.controller;

import br.com.estudos.gestao_vagas.modules.candidato.dto.AuthCandidatoDTO;
import br.com.estudos.gestao_vagas.modules.candidato.useCases.AuthCandidatoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidato")
public class AuthCandidatoController {


  @Autowired
  private AuthCandidatoUseCase authCandidatoUseCase;
  @PostMapping("/auth")
  public ResponseEntity<Object> auth(@RequestBody AuthCandidatoDTO authCandidatoDTO){

    try {
      var token = this.authCandidatoUseCase.execute(authCandidatoDTO);
      return ResponseEntity.ok().body(token);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
