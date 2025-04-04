package br.com.estudos.gestao_vagas.modules.empresa.useCases;

import br.com.estudos.gestao_vagas.exceptions.UserFoundException;
import br.com.estudos.gestao_vagas.modules.candidato.CandidatoEntity;
import br.com.estudos.gestao_vagas.modules.candidato.CandidatoRepository;
import br.com.estudos.gestao_vagas.modules.empresa.entidades.EmpresaEntity;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarEmpresaUseCase {

  @Autowired
  private EmpresaRepository empresaRepository;


  public EmpresaEntity execute(EmpresaEntity empresa){
    this.empresaRepository
        .findByUsernameOrEmail(empresa.getUsername(),empresa.getEmail())
        .ifPresent((user) ->{
          throw new UserFoundException();
        });
    return this.empresaRepository.save(empresa);
  }
}
