package br.com.estudos.gestao_vagas.modules.empresa.controllers;


import br.com.estudos.gestao_vagas.modules.empresa.dto.CriarVagaDTO;
import br.com.estudos.gestao_vagas.modules.empresa.entidades.VagasEntity;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.VagaRepository;
import br.com.estudos.gestao_vagas.modules.empresa.useCases.CriarEmpresaUseCase;
import br.com.estudos.gestao_vagas.modules.empresa.useCases.CriarVagaUseCase;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/empresa/vaga")
public class VagaController {

  @Autowired
  private CriarVagaUseCase criarVagaUseCase;

  @PostMapping("/")
  @PreAuthorize("hasRole('EMPRESA')")
  @Tag(name = "Vagas", description = "Informações das vagas")
  @Operation(summary = "Cadastro de vagas",description = "Responsável por cadastrar as vagas de uma empresa")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = VagasEntity.class))
      })
  })
  @SecurityRequirement(name = "jwt_auth")
  public VagasEntity create(@Valid@RequestBody CriarVagaDTO vagaDTO, HttpServletRequest request){
    var idDaEmpresa = request.getAttribute("id_empresa");

    var vagaEntity = VagasEntity.builder()
        .beneficios(vagaDTO.getBeneficios())
        .id_empresa(UUID.fromString(idDaEmpresa.toString()))
        .description(vagaDTO.getDescription())
        .level(vagaDTO.getLevel())
        .build();
    return this.criarVagaUseCase.execute(vagaEntity);
  }
}
