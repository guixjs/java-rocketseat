package br.com.estudos.gestao_vagas.modules.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthEmpresaResponseDTO {
  private String acess_token;
  private Long expires_in;
}
