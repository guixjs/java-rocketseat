package br.com.estudos.gestao_vagas.modules.candidato;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidatoRepository extends JpaRepository<CandidatoEntity, UUID> {
  Optional <CandidatoEntity> findByUsernameOrEmail(String username, String email);
  Optional <CandidatoEntity> findByUsername(String username);
}
