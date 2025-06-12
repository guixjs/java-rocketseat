package br.com.estudos.gestao_vagas.modules.empresa.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity(name="empresa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;

  @Column(unique = true)
  @NotBlank()
  @Pattern(regexp = "\\S+" ,message = "O campo username não deve conter espaços")
  private String username;

  @Column(unique = true)
  @Email(message = "O campo deve conter um email válido")
  private String email;

  @Length(min = 6,max = 100,message = "A senha deve ter de 6 a 12 dígitos")
  private String password;
  private String website;
  private String description;

  @CreationTimestamp
  private LocalDateTime created_at;


}


