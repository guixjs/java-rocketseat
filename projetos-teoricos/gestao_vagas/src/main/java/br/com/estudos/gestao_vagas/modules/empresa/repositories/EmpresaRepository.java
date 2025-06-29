package br.com.estudos.gestao_vagas.modules.empresa.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.gestao_vagas.modules.empresa.entidades.EmpresaEntity;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, UUID> {
  Optional<EmpresaEntity> findByUsernameOrEmail(String username, String email);
  Optional<EmpresaEntity> findByUsername(String username);
}
