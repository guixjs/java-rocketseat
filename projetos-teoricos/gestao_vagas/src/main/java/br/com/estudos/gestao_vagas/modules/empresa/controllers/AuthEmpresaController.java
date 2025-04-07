package br.com.estudos.gestao_vagas.modules.empresa.controllers;

import br.com.estudos.gestao_vagas.modules.empresa.dto.AuthEmpresaDTO;
import br.com.estudos.gestao_vagas.modules.empresa.useCases.AuthEmpresaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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
  public String create(@RequestBody AuthEmpresaDTO authEmpresaDTO){
    return this.authEmpresaUseCase.execute(authEmpresaDTO);
  }

}
