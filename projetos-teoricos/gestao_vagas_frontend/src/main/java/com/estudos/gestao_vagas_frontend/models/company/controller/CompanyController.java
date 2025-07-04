package com.estudos.gestao_vagas_frontend.models.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estudos.gestao_vagas_frontend.models.company.dto.CreateCompanyDTO;
import com.estudos.gestao_vagas_frontend.models.company.dto.CreateJobDTO;
import com.estudos.gestao_vagas_frontend.models.company.service.CreateCompanyService;
import com.estudos.gestao_vagas_frontend.models.company.service.CreateJobService;
import com.estudos.gestao_vagas_frontend.models.company.service.ListAllJobsCompanyService;
import com.estudos.gestao_vagas_frontend.models.company.service.LoginCompanyService;
import com.estudos.gestao_vagas_frontend.utils.FormatErrorMessage;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CreateCompanyService createCompanyService;

  @Autowired
  private LoginCompanyService loginCompanyService;

  @Autowired
  private CreateJobService createJobService;

  @Autowired
  private ListAllJobsCompanyService listAllJobsCompanyService;

  @GetMapping("/create")
  public String create(Model model) {
    model.addAttribute("company", new CreateCompanyDTO());
    return "/company/create";
  }

  @PostMapping("/create")
  public String save(Model model, CreateCompanyDTO createCompanyDTO) {
    try {
      this.createCompanyService.execute(createCompanyDTO);
      model.addAttribute("company", new CreateCompanyDTO());
    } catch (HttpClientErrorException ex) {
      model.addAttribute("error_message", FormatErrorMessage.formatErrorMessage(ex.getResponseBodyAsString()));
      model.addAttribute("company", createCompanyDTO);
    }
    return "company/create";
  }

  @GetMapping("/login")
  public String loin() {
    return "company/login";
  }

  @PostMapping("/signIn")
  public String signIn(RedirectAttributes redirectAttributes, HttpSession session, String username, String password) {
    try {
      var token = this.loginCompanyService.login(username, password);

      var grants = token.getRoles().stream()
          .map(role -> new SimpleGrantedAuthority("ROLE_" +
              role.toString().toUpperCase()))
          .toList();
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, grants);
      auth.setDetails(token.getAccess_token());

      SecurityContextHolder.getContext().setAuthentication(auth);
      SecurityContext securityContext = SecurityContextHolder.getContext();
      session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
      session.setAttribute("token", token);

      return "redirect:/company/jobs";
    } catch (HttpClientErrorException e) {
      redirectAttributes.addFlashAttribute("error_message", "Usuário/senha incorretos");
      return "redirect:/company/jobs";
    }
  }

  @GetMapping("/jobs")
  @PreAuthorize("hasRole('EMPRESA')")
  public String jobs(Model model) {
    model.addAttribute("jobs", new CreateJobDTO());
    return "company/jobs";
  }

  @PostMapping("/jobs")
  @PreAuthorize("hasRole('EMPRESA')")
  public String createJobs(CreateJobDTO jobs) {
    this.createJobService.execute(jobs, getToken());
    return "redirect:/company/jobs";
  }

  @GetMapping("/jobs/list")
  @PreAuthorize("hasRole('EMPRESA')")
  public String list(Model model) {
    var result = this.listAllJobsCompanyService.execute(getToken());
    model.addAttribute("jobs", result);
    return "company/list";
  }

  private String getToken() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getDetails().toString();
  }

}
