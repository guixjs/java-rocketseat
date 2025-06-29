package br.com.estudos.gestao_vagas.modules.candidato.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "aplicar_vaga")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AplicarVagaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  // @ManyToOne
  // @JoinColumn(name = "candidato_id",insertable = false,nullable = false)
  // private CandidatoEntity candidatoEntity;
  
  // @ManyToOne
  // @JoinColumn(name = "vaga_id",insertable = false,nullable = false)
  // private VagasEntity vagasEntity;

  @Column(name = "candidato_id")
  private UUID idCandidato;
  
  @Column(name = "vaga_id")
  private UUID idVaga;


  @CreationTimestamp
  private LocalDateTime created_at;
}
