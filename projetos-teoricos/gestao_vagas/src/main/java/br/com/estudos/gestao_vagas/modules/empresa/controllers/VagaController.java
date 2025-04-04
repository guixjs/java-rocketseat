package br.com.estudos.gestao_vagas.modules.empresa.controllers;


import br.com.estudos.gestao_vagas.modules.empresa.entidades.VagasEntity;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.VagaRepository;
import br.com.estudos.gestao_vagas.modules.empresa.useCases.CriarEmpresaUseCase;
import br.com.estudos.gestao_vagas.modules.empresa.useCases.CriarVagaUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaga")
public class VagaController {

  @Autowired
  private CriarVagaUseCase criarVagaUseCase;

  @PostMapping("/")
  public VagasEntity create(@Valid@RequestBody VagasEntity vaga){
    return this.criarVagaUseCase.execute(vaga);
  }
}
