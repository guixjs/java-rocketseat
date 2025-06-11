package br.com.estudos.gestao_vagas.modules.candidato.useCase;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import br.com.estudos.gestao_vagas.modules.candidato.useCases.AplicarVagaUseCase;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.VagaRepository;


@ExtendWith(MockitoExtension.class)
public class AplicarVagaUseCaseTest {

  @InjectMocks
  private AplicarVagaUseCase aplicarVagaUseCase;

  @Mock
  private CandidatoRepository candidatoRepository;

  @Mock
  private VagaRepository vagaRepository;


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
  
}
  