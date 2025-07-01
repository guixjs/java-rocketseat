package br.com.estudos.gestao_vagas.modules.candidato.useCases;

import br.com.estudos.gestao_vagas.modules.candidato.CandidatoRepository;
import br.com.estudos.gestao_vagas.modules.candidato.dto.AuthCandidatoDTO;
import br.com.estudos.gestao_vagas.modules.candidato.dto.AuthCandidatoResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidatoUseCase {

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  @Autowired
  private CandidatoRepository candidatoRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidatoResponseDTO execute(AuthCandidatoDTO authCandidatoDTO) throws AuthenticationException {
    var candidato = this.candidatoRepository.findByUsername(authCandidatoDTO.username())
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("Usename/password incorreto");
        });

    var verificacaoSenha = passwordEncoder
        .matches(authCandidatoDTO.password(), candidato.getPassword());

    if (!verificacaoSenha) {
      throw new AuthenticationException();
    }

    var roles = Arrays.asList("CANDIDATO");

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var expries_in = Instant.now().plus(Duration.ofMinutes(20));
    var token = JWT.create()
        .withIssuer("javagas")
        .withExpiresAt(expries_in)
        .withClaim("roles", roles)
        .withSubject((candidato.getId()).toString())
        .sign(algorithm);

    var authCandidatoResponse = AuthCandidatoResponseDTO.builder()
        .access_token(token)
        .expries_in(expries_in.toEpochMilli())
        .roles(roles)
        .build();

    return authCandidatoResponse;
  }
}
