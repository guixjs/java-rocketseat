package com.estudos.gestao_vagas_frontend.models.candidate.dto;

import lombok.Data;

@Data
public class CreateCandidateDTO {

  private String usermanem;
  private String password;
  private String email;
  private String name;
  private String description;

}
