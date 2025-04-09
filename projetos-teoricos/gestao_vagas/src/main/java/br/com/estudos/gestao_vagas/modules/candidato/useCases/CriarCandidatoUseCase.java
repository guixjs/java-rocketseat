package br.com.estudos.gestao_vagas.modules.candidato.useCases;

import br.com.estudos.gestao_vagas.exceptions.UserFoundException;
import br.com.estudos.gestao_vagas.modules.candidato.CandidatoEntity;
import br.com.estudos.gestao_vagas.modules.candidato.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CriarCandidatoUseCase {
  @Autowired
  private CandidatoRepository candidatoRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidatoEntity execute(CandidatoEntity candidato){
    this.candidatoRepository
        .findByUsernameOrEmail(candidato.getUsername(), candidato.getEmail())
        .ifPresent((user)->{
          throw new UserFoundException();
        });
    var password = passwordEncoder.encode(candidato.getPassword());
    candidato.setPassword(password);
    return this.candidatoRepository.save(candidato);
  }
}
