package br.com.estudos.gestao_vagas.modules.candidato.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilCandidateResponseDTO {

  private UUID id;
  private String name;
  private String username;
  private String description;
  private String email;

}
