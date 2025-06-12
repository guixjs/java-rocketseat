package br.com.estudos.gestao_vagas.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestesUtils {
  public static String objetcToJson(Object obj){

    try{
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(obj);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  } 

  public static String generateToken(UUID idEmpresa, String secretKey){
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create().withIssuer("javagas")
        .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
        .withSubject((idEmpresa).toString())
        .withClaim("roles", Arrays.asList("EMPRESA"))
        .sign(algorithm);

        return token;
  }
}
