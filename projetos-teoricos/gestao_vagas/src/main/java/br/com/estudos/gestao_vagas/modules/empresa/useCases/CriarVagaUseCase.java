package br.com.estudos.gestao_vagas.modules.empresa.useCases;

import br.com.estudos.gestao_vagas.modules.empresa.entidades.VagasEntity;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarVagaUseCase{

  @Autowired
  private VagaRepository vagaRepository;

  public VagasEntity execute(VagasEntity vaga){
    return this.vagaRepository.save(vaga);
  }
}
