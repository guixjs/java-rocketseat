package br.com.estudos.gestao_vagas.modules.candidato.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudos.gestao_vagas.modules.candidato.model.AplicarVagaEntity;

public interface AplicarVagaRepository extends JpaRepository<AplicarVagaEntity,UUID> {
    
}
