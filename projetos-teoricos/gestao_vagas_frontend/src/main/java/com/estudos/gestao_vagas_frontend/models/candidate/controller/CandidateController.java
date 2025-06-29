package com.estudos.gestao_vagas_frontend.models.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estudos.gestao_vagas_frontend.models.candidate.service.CandidateService;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CandidateService candidateService;

  @GetMapping("/login")
  public String login() {
    return "candidate/login";
  }

  @PostMapping("/signIn")
  public String signIn(RedirectAttributes redirectAttributes, String username, String password) {
    try {
      var token = this.candidateService.login(username, password);
      return "candidate/profile";
    } catch (HttpClientErrorException e) {
      redirectAttributes.addFlashAttribute("error_message", "Usuário/senha incorretos");
      return "redirect:/candidate/login";
    }
  }

}
