package com.estudos.gestao_vagas_frontend.models.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
  private String description;
  private String level;
  private String beneficios;
}
