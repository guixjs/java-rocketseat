package com.estudos.gestao_vagas_frontend.models.company.dto;

import lombok.Data;

@Data
public class CreateCompanyDTO {
  private String username;
  private String email;
  private String website;
  private String description;
  private String name;
  private String password;
}
