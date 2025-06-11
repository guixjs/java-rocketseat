package br.com.estudos.gestao_vagas.modules.candidato.useCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.estudos.gestao_vagas.exceptions.UserNotFoundException;
import br.com.estudos.gestao_vagas.exceptions.VagaNotFoundException;
import br.com.estudos.gestao_vagas.modules.candidato.CandidatoEntity;
import br.com.estudos.gestao_vagas.modules.candidato.CandidatoRepository;
import br.com.estudos.gestao_vagas.modules.candidato.model.AplicarVagaEntity;
import br.com.estudos.gestao_vagas.modules.candidato.repository.AplicarVagaRepository;
import br.com.estudos.gestao_vagas.modules.candidato.useCases.AplicarVagaUseCase;
import br.com.estudos.gestao_vagas.modules.empresa.entidades.VagasEntity;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.VagaRepository;


@ExtendWith(MockitoExtension.class)
public class AplicarVagaUseCaseTest {

  @InjectMocks
  private AplicarVagaUseCase aplicarVagaUseCase;

  @Mock
  private CandidatoRepository candidatoRepository;

  @Mock
  private VagaRepository vagaRepository;

  @Mock
  private AplicarVagaRepository aplicarVagaRepository;



  @Test
  @DisplayName("Não deve ser possível aplicar para uma vaga se o candidato não for encontrado")
  public void should_not_be_able_apply_job_whit_candidate_not_found() {
      assertThrows(UserNotFoundException.class, () -> {
          aplicarVagaUseCase.execute(null, null);
      });
  }

  @Test
  @DisplayName("Não deve ser possível aplicar para uma vaga se a vaga não for encontrada")
  public void should_not_be_able_apply_job_whit_job_not_found(){


    var idSimulado = UUID.randomUUID();
    var candidato = new CandidatoEntity();
    candidato.setId(idSimulado);

    when(candidatoRepository.findById(idSimulado)).thenReturn(Optional.of(candidato));

    assertThrows(VagaNotFoundException.class, () -> {
          aplicarVagaUseCase.execute(idSimulado, null);
      });

  }
  
  @Test
  public void should_be_able_to_create_a_new_apply_job(){
    var idCandidato = UUID.randomUUID();
    var idVaga = UUID.randomUUID();

    var aplicarVaga = AplicarVagaEntity.builder()
      .idCandidato(idCandidato)
      .idVaga(idVaga)
      .build();

    new AplicarVagaEntity();
    var applyJobCreated = AplicarVagaEntity.builder()
      .id(UUID.randomUUID())
      .build();

    aplicarVaga.setId(UUID.randomUUID());

    when(candidatoRepository.findById(idCandidato)).thenReturn(Optional.of(new CandidatoEntity()));
    when(vagaRepository.findById(idVaga)).thenReturn(Optional.of(new VagasEntity()));
    when(aplicarVagaRepository.save(any(AplicarVagaEntity.class))).thenReturn(applyJobCreated);


    var resultado = aplicarVagaUseCase.execute(idCandidato, idVaga);

    assertThat(resultado).hasFieldOrProperty("id");
    assertNotNull(resultado.getId());
    
  }

}
  