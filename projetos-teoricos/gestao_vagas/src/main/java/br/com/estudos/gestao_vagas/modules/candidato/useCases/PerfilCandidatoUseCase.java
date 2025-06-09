package br.com.estudos.gestao_vagas.modules.candidato.useCases;

import br.com.estudos.gestao_vagas.modules.candidato.CandidatoRepository;
import br.com.estudos.gestao_vagas.modules.candidato.dto.PerfilCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PerfilCandidatoUseCase {


  @Autowired
  private CandidatoRepository candidatoRepository;

  public PerfilCandidateResponseDTO execute(UUID idCandidato){
    var candidato = this.candidatoRepository.findById(idCandidato)
        .orElseThrow(()->{
          throw new UsernameNotFoundException("User não encontrado");
        });
    return PerfilCandidateResponseDTO.builder()
        .description(candidato.getDescription())
        .name(candidato.getName())
        .email(candidato.getEmail())
        .id(candidato.getId())
        .build();
  }
}
