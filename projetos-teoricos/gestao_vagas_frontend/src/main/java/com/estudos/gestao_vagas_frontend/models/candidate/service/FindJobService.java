package com.estudos.gestao_vagas_frontend.models.candidate.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.estudos.gestao_vagas_frontend.models.candidate.dto.JobDTO;

@Service
public class FindJobService {

  public String execute(String token, String filter) {
    RestTemplate rt = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(token);

    HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/candidato/jobs")
        .queryParam("filter", filter);

    ParameterizedTypeReference<List<JobDTO>> responseEntity = new ParameterizedTypeReference<List<JobDTO>>() {

    };
    try {
      var result = rt.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
      return result.getBody();
    } catch (Unauthorized ex) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);

    }
  }

}
