package br.com.estudos.gestao_vagas.modules.empresa.useCases;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.BadCredentialsException;
import br.com.estudos.gestao_vagas.modules.empresa.dto.AuthEmpresaDTO;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.EmpresaRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthEmpresaUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private EmpresaRepository empresaRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthEmpresaDTO authEmpresaDTO){
    var empresa = this.empresaRepository.findByUsername(authEmpresaDTO.getUsername()).orElseThrow(
        ()->{
          throw new UsernameNotFoundException("Empresa não encontrada");
        }
    );
    var verificacaoSenha = this.passwordEncoder.matches(authEmpresaDTO.getPassword(),empresa.getPassword());

    if(!verificacaoSenha){
      throw new BadCredentialsException("Senha inválida.");
    }
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create().withIssuer("javagas")
        .withSubject((empresa.getId()).toString())
        .sign(algorithm);

    return token;

  }

}
