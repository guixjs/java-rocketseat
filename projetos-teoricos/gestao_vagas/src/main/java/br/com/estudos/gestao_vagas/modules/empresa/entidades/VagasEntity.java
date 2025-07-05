package br.com.estudos.gestao_vagas.modules.empresa.entidades;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Column(name = "id_empresa", nullable = false)
  private UUID idEmpresa;

  @CreationTimestamp
  private LocalDateTime created_at;
}
