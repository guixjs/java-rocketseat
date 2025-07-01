package com.estudos.gestao_vagas_frontend.models.candidate.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estudos.gestao_vagas_frontend.models.candidate.dto.ProfileUserDTO;

@Service
public class ProfileCandidateService {

  public ProfileUserDTO execute(String token) {
    RestTemplate rt = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

    HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

    var result = rt.exchange("http://localhost:8080/candidato/", HttpMethod.GET, request, ProfileUserDTO.class);
    return result.getBody();
  }
}
