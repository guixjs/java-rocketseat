package com.estudos.gestao_vagas_frontend.models.candidate.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplyJobService {
  public String execute(String token, UUID idVaga) {
    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, String> data = new HashMap<>();

    data.put("idJob", idVaga.toString());

    HttpEntity<UUID> request = new HttpEntity<>(idVaga, headers);

    var result = rt.postForObject("http://localhost:8080/candidato/jobs/apply", request, String.class);

    return result;
  }
}
