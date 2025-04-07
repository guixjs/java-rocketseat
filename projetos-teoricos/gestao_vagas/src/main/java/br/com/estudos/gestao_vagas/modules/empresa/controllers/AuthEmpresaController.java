package br.com.estudos.gestao_vagas.modules.empresa.controllers;

import br.com.estudos.gestao_vagas.modules.empresa.dto.AuthEmpresaDTO;
import br.com.estudos.gestao_vagas.modules.empresa.useCases.AuthEmpresaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthEmpresaController {


  @Autowired
  private AuthEmpresaUseCase authEmpresaUseCase;

  @PostMapping("/empresa")
  public ResponseEntity<Object> create(@RequestBody AuthEmpresaDTO authEmpresaDTO){
    try {
      var result = this.authEmpresaUseCase.execute(authEmpresaDTO);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

  }

}
