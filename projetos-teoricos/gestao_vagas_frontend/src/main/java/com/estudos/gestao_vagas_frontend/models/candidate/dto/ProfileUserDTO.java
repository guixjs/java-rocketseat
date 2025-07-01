package com.estudos.gestao_vagas_frontend.models.candidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserDTO {

  private String email;
  private UUID id;
  private String description;
  private String username;
  private String name;

}
