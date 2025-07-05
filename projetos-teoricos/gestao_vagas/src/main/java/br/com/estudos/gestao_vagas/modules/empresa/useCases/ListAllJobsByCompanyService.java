package br.com.estudos.gestao_vagas.modules.empresa.useCases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudos.gestao_vagas.modules.empresa.entidades.VagasEntity;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.VagaRepository;

@Service
public class ListAllJobsByCompanyService {
  @Autowired
  private VagaRepository vagaRepository;

  public List<VagasEntity> execute(UUID idEmpresa) {
    return this.vagaRepository.findByIdEmpresa(idEmpresa);
  }
}
