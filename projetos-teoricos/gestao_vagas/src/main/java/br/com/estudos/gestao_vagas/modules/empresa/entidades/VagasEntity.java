package br.com.estudos.gestao_vagas.modules.empresa.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.Name;
import jdk.jfr.Relational;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "vagas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VagasEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String description;
  private String beneficios;
  @NotBlank(message = "Esse campo é obrigatório")
  private String level;

  @ManyToOne()
  @JoinColumn(name = "id_empresa", insertable = false, updatable = false)
  private EmpresaEntity empresaEntity;

  @Column(name = "id_empresa",nullable = false)
  private UUID id_empresa;

  @CreationTimestamp
  private LocalDateTime created_at;
}
