package br.com.estudos.gestao_vagas.modules.empresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriarVagaDTO {

  @Schema(example = "Vaga para pessoa desenvolvedora junior", requiredMode = Schema.RequiredMode.REQUIRED)
  private String description;
  @Schema(example = "Muitos",requiredMode = Schema.RequiredMode.REQUIRED)
  private String beneficios;
  @Schema(example = "Junior",requiredMode = Schema.RequiredMode.REQUIRED)
  private String level;

}
