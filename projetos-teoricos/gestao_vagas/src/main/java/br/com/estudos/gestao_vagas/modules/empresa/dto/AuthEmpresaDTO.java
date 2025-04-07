package br.com.estudos.gestao_vagas.modules.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthEmpresaDTO {

  private String username;
  private String password;

}
