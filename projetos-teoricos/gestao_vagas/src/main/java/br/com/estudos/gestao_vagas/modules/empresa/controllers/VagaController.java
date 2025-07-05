package br.com.estudos.gestao_vagas.modules.empresa.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.gestao_vagas.modules.empresa.dto.CriarVagaDTO;
import br.com.estudos.gestao_vagas.modules.empresa.entidades.VagasEntity;
import br.com.estudos.gestao_vagas.modules.empresa.useCases.CriarVagaUseCase;
import br.com.estudos.gestao_vagas.modules.empresa.useCases.ListAllJobsByCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa/vaga")
public class VagaController {

  @Autowired
  private CriarVagaUseCase criarVagaUseCase;

  @Autowired
  private ListAllJobsByCompanyService listAllJobs;

  @PostMapping("/")
  @PreAuthorize("hasRole('EMPRESA')")
  @Tag(name = "Vagas", description = "Informações das vagas")
  @Operation(summary = "Cadastro de vagas", description = "Responsável por cadastrar as vagas de uma empresa")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = VagasEntity.class))
      })
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> create(@Valid @RequestBody CriarVagaDTO vagaDTO, HttpServletRequest request) {
    var idDaEmpresa = request.getAttribute("idEmpresa");

    try {
      var vagaEntity = VagasEntity.builder()
          .beneficios(vagaDTO.getBeneficios())
          .idEmpresa(UUID.fromString(idDaEmpresa.toString()))
          .description(vagaDTO.getDescription())
          .level(vagaDTO.getLevel())
          .build();
      var result = this.criarVagaUseCase.execute(vagaEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());

    }

  }

  @GetMapping("/")
  @PreAuthorize("hasRole('EMPRESA')")
  @Tag(name = "Vagas", description = "Listagem das vagas")
  @Operation(summary = "Cadastro de vagas", description = "Responsável por listar as vagas de uma empresa")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = VagasEntity.class))
      })
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> listByCompany(HttpServletRequest request) {
    var idDaEmpresa = request.getAttribute("idEmpresa");
    var result = this.listAllJobs.execute(UUID.fromString(idDaEmpresa.toString()));
    return ResponseEntity.ok().body(result);
  }
}
