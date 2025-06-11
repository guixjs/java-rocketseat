package br.com.estudos.gestao_vagas.modules.candidato.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudos.gestao_vagas.exceptions.UserNotFoundException;
import br.com.estudos.gestao_vagas.exceptions.VagaNotFoundException;
import br.com.estudos.gestao_vagas.modules.candidato.CandidatoRepository;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.VagaRepository;

@Service
public class AplicarVagaUseCase {
  
  @Autowired
  private CandidatoRepository candidatoRepository;

  @Autowired
  private VagaRepository vagaRepository;

  public void execute(UUID idCandidato, UUID idVaga){
    this.candidatoRepository.findById(idCandidato)
    .orElseThrow(()->{
      throw new UserNotFoundException();
    });


    this.vagaRepository.findById(idVaga)
    .orElseThrow(()->{
      throw new VagaNotFoundException();
    });
  }

}
