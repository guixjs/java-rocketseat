package br.com.estudos.gestao_vagas.modules.candidato;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;
import java.util.UUID;

@Data //annotation do lombok para criar os getters e setters
@Entity(name = "candidato")
public class CandidatoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id ;
  private String name;

  @NotBlank()
  @Pattern(regexp = "\\S+" ,message = "O campo username não deve conter espaços")
  private String username;

  @Email(message = "O campo deve conter um email válido")
  private String email;

  @Length(min = 6,max = 12)
  private String password;
  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime created_at;

}
