package com.estudos.gestao_vagas_frontend.models.candidate.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estudos.gestao_vagas_frontend.models.candidate.dto.CreateCandidateDTO;

@Service
public class CreateCandidateService {

  public void execute(CreateCandidateDTO createCandidateDTO) {
    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<CreateCandidateDTO> request = new HttpEntity<>(createCandidateDTO, headers);

    var result = rt.postForObject("http://localhost:8080/candidato/", request, String.class);
  }
}
