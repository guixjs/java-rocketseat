package br.com.estudos.gestao_vagas.modules.candidato.useCases;

import br.com.estudos.gestao_vagas.exceptions.UserFoundException;
import br.com.estudos.gestao_vagas.modules.candidato.CandidatoEntity;
import br.com.estudos.gestao_vagas.modules.candidato.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarCandidatoUseCase {
  @Autowired
  private CandidatoRepository candidatoRepository;

  public CandidatoEntity execute(CandidatoEntity candidato){
    this.candidatoRepository.
        findByUsernameOrEmail(candidato.getUsername(), candidato.getEmail()).ifPresent((user)->{
          throw new UserFoundException();
        });
    return this.candidatoRepository.save(candidato);
  }
}
