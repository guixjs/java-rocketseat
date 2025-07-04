package br.com.estudos.gestao_vagas.modules.empresa.useCases;

import br.com.estudos.gestao_vagas.modules.empresa.dto.AuthEmpresaResponseDTO;
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

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthEmpresaUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private EmpresaRepository empresaRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthEmpresaResponseDTO execute(AuthEmpresaDTO authEmpresaDTO) {
    var empresa = this.empresaRepository.findByUsername(authEmpresaDTO.getUsername())
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("Empresa não encontrada");
        });
    var verificacaoSenha = this.passwordEncoder.matches(authEmpresaDTO.getPassword(), empresa.getPassword());

    if (!verificacaoSenha) {
      throw new BadCredentialsException("Usuário ou senha inválida.");
    }
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create().withIssuer("javagas")
        .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
        .withSubject((empresa.getId()).toString())
        .withClaim("roles", Arrays.asList("EMPRESA"))
        .sign(algorithm);

    var roles = Arrays.asList("EMPRESA");

    var expiresIn = Instant.now().plus(Duration.ofHours(2));
    return AuthEmpresaResponseDTO.builder()
        .acess_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .roles(roles)
        .build();

  }

}
