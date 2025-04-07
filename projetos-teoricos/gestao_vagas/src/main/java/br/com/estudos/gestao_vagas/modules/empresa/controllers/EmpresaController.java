package br.com.estudos.gestao_vagas.modules.empresa.controllers;


import br.com.estudos.gestao_vagas.modules.empresa.entidades.EmpresaEntity;
import br.com.estudos.gestao_vagas.modules.empresa.useCases.CriarEmpresaUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

  @Autowired
  private CriarEmpresaUseCase criarEmpresaUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody EmpresaEntity empresa){
    try {
      var result = this.criarEmpresaUseCase.execute(empresa);
      return ResponseEntity.ok().body(result);

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
