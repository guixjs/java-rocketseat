package br.com.estudos.gestao_vagas.modules.candidato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;
@Data //annotation do lombok para criar os getters e setters
public class CandidatoEntity {
  private UUID id ;
  private String name;
  @Pattern(regexp = "^(?!\\s*$).+" ,message = "O campo username não deve conter espaços")
  private String username;
  @Email(message = "O campo deve conter um email válido")
  private String email;
  @Length(min = 6,max = 12)
  private String password;
  private String description;
  private String curriculum;

}
