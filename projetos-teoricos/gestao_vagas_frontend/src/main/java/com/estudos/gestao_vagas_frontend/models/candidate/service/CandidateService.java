package com.estudos.gestao_vagas_frontend.models.candidate.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estudos.gestao_vagas_frontend.models.candidate.dto.Token;

@Service
public class CandidateService {

  public Token login(String username, String password) {
    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, String> data = new HashMap<>();

    data.put("username", username);
    data.put("password", password);

    HttpEntity<Map<String, String>> request = new HttpEntity<>(data);

    var result = rt.postForObject("http://localhost:8080/candidato/auth", request, Token.class);

    return result;
  }
}
