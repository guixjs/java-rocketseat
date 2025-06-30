package br.com.estudos.gestao_vagas.modules.candidato.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCandidatoResponseDTO {
  private String access_token;
  private Long expries_in;
  private List<String> roles;
}
