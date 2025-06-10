package br.com.estudos.gestao_vagas.modules.candidato.controller;

import br.com.estudos.gestao_vagas.modules.candidato.CandidatoEntity;
import br.com.estudos.gestao_vagas.modules.candidato.dto.PerfilCandidateResponseDTO;
import br.com.estudos.gestao_vagas.modules.candidato.useCases.CriarCandidatoUseCase;
import br.com.estudos.gestao_vagas.modules.candidato.useCases.ListarVagasPorFiltroUseCase;
import br.com.estudos.gestao_vagas.modules.candidato.useCases.PerfilCandidatoUseCase;
import br.com.estudos.gestao_vagas.modules.empresa.entidades.VagasEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

  @Autowired
  private CriarCandidatoUseCase criarCandidatoUseCase;

  @Autowired
  private PerfilCandidatoUseCase perfilCandidatoUseCase;

  @Autowired
  private ListarVagasPorFiltroUseCase listarVagasPorFiltroUseCase;

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
  @Tag(name = "Candidato", description = "Informações do candidato")
  @Operation(summary = "Listagem de vagas disponíveis para o candidato",
      description = "Responsável por retornar as informações do perfil do candidato")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = PerfilCandidateResponseDTO.class)
              )
          )}
      )
      ,@ApiResponse(responseCode = "400", description = "User not found")
  })

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

  @GetMapping("/job")
  @PreAuthorize("hasRole('CANDIDATO')")
  @Tag(name = "Candidato", description = "Informações do candidato")
  @Operation(summary = "Listagem de vagas disponíveis para o candidato",description = "Lista de vagas")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(
              array = @ArraySchema(
                  schema = @Schema(implementation = VagasEntity.class)
              )
          )
      })
  })
  @SecurityRequirement(name = "jwt_auth")
  public List<VagasEntity> findJobFilter(@RequestParam String filter){
    return this.listarVagasPorFiltroUseCase.execute(filter);

  }
}
