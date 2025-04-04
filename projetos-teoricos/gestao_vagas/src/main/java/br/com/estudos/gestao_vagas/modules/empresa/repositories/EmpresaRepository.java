package br.com.estudos.gestao_vagas.modules.empresa.repositories;

import br.com.estudos.gestao_vagas.modules.candidato.CandidatoEntity;
import br.com.estudos.gestao_vagas.modules.empresa.entidades.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, UUID> {
  Optional<EmpresaEntity> findByUsernameOrEmail(String username, String email);
}
