package br.unipar.consultorio.model.dto;

import br.unipar.consultorio.enums.EspecialidadeENUM;
import br.unipar.consultorio.enums.StatusENUM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente ao Médico")
public class MedicoDTO {
    private String nome;
    private String email;
    private String crm;
    private EspecialidadeENUM especialidade;
    private StatusENUM statusENUM;
}
