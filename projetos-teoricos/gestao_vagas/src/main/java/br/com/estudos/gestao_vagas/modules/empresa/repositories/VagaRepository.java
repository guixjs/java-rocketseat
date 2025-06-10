package br.com.estudos.gestao_vagas.modules.empresa.repositories;

import br.com.estudos.gestao_vagas.modules.empresa.entidades.VagasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VagaRepository extends JpaRepository<VagasEntity, UUID> {

  List<VagasEntity> findByDescriptionContainingIgnoreCase(String description);
}
