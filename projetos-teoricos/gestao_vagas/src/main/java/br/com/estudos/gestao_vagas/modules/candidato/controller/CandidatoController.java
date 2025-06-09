package br.com.estudos.gestao_vagas.modules.candidato.controller;

import br.com.estudos.gestao_vagas.modules.candidato.CandidatoEntity;
import br.com.estudos.gestao_vagas.modules.candidato.useCases.CriarCandidatoUseCase;
import br.com.estudos.gestao_vagas.modules.candidato.useCases.PerfilCandidatoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

  @Autowired
  private CriarCandidatoUseCase criarCandidatoUseCase;

  @Autowired
  private PerfilCandidatoUseCase perfilCandidatoUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidatoEntity candidato){
    try {
      var result =this.criarCandidatoUseCase.execute(candidato);
      return ResponseEntity.ok().body(result);
    }catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  public ResponseEntity<Object> get(HttpServletRequest request){
    var idUser = request.getAttribute("candidato_id");
    try {

      var perfil = this.perfilCandidatoUseCase.execute(UUID.fromString(idUser.toString()));
      return ResponseEntity.ok().body(perfil);
    }catch (Exception e){
      e.printStackTrace();
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
