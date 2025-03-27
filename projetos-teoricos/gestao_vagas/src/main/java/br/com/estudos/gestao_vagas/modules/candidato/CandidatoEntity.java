package br.com.estudos.gestao_vagas.modules.candidato;

import lombok.Data;

import java.util.UUID;
@Data //annotation do lombok para criar os getters e setters
public class CandidatoEntity {
  private UUID id ;
  private String name;
  private String username;
  private String email;
  private String password;
  private String description;
  private String curriculum;

}
