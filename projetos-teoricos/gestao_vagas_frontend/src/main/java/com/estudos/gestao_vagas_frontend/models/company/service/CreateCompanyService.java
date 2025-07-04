package com.estudos.gestao_vagas_frontend.models.company.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estudos.gestao_vagas_frontend.models.company.dto.CreateCompanyDTO;

@Service
public class CreateCompanyService {
  public void execute(CreateCompanyDTO createCompanyDTO) {
    RestTemplate rt = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<CreateCompanyDTO> request = new HttpEntity<>(createCompanyDTO, headers);

    rt.postForObject("http://localhost:8080/company/", request, String.class);
  }
}
