package br.com.estudos.gestao_vagas.modules.candidato.useCases;

import br.com.estudos.gestao_vagas.modules.empresa.entidades.VagasEntity;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarVagasPorFiltroUseCase {

  @Autowired
  private VagaRepository vagaRepository;

  public List<VagasEntity> execute(String filter){
    return this.vagaRepository.findByDescriptionContainingIgnoreCase(filter);
  }
}
