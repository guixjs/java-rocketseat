package com.estudos.gestao_vagas_frontend.models.company.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estudos.gestao_vagas_frontend.models.company.dto.CreateJobDTO;

@Service
public class CreateJobService {
  public String execute(CreateJobDTO job, String token) {
    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<CreateJobDTO> request = new HttpEntity<>(job, headers);

    var result = rt.postForObject("http://localhost:8080/empresa/vaga/", request, String.class);

    return result;
  }
}
