package br.com.estudos.gestao_vagas.modules.empresa.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.estudos.gestao_vagas.modules.empresa.dto.CriarVagaDTO;
import br.com.estudos.gestao_vagas.modules.empresa.entidades.EmpresaEntity;
import br.com.estudos.gestao_vagas.modules.empresa.repositories.EmpresaRepository;
import br.com.estudos.gestao_vagas.utils.TestesUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CriarVagaControllerTest {

  
  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private EmpresaRepository empresaRepository;

  @Before
  public void setup(){
    mvc = MockMvcBuilders.webAppContextSetup(context)
      .apply(SecurityMockMvcConfigurers.springSecurity())
      .build();
  }
  
  @Test
  @DisplayName("Deve ser possível criar uma nova vaga")
  public void should_be_able_to_create_a_new_job() throws Exception{

    var empresa = EmpresaEntity.builder()
      .description("EMPRESA_DESCRIPTION")
      .email("email@empresa.com")
      .password("12345678")
      .username("EMPRESA_USERNAME")
      .name("EMPRESA_NAME")
      .build();

    empresa = empresaRepository.saveAndFlush(empresa);

    var vagaDTO = CriarVagaDTO.builder()
      .beneficios("BENEFICIOS_TESTES")
      .description("DESCRICAO_TESTE")
      .level("NIVEL_TESTES")
      .build();

    var resultado = mvc.perform(MockMvcRequestBuilders.post("/empresa/vaga/")
      .contentType(MediaType.APPLICATION_JSON)
      .content(TestesUtils.objetcToJson(vagaDTO))
      .header("Authorization",TestesUtils.generateToken(empresa.getId(),"JAVAGAS_@123#")))
      .andExpect(MockMvcResultMatchers.status().isOk());

      System.out.println(resultado);
  }

  @Test
  public void should_not_be_able_to_create_a_new_job_with_company_not_found() throws Exception{

    var vagaDTO = CriarVagaDTO.builder()
      .beneficios("BENEFICIOS_TESTES")
      .description("DESCRICAO_TESTE")
      .level("NIVEL_TESTES")
      .build();

      mvc.perform(MockMvcRequestBuilders.post("/empresa/vaga/")
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestesUtils.objetcToJson(vagaDTO))
          .header("Authorization",TestesUtils.generateToken(UUID.randomUUID(),"JAVAGAS_@123#")))
          .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
  
}
