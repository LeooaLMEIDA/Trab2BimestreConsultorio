package br.unipar.consultorio.model.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente ao Paciente")
public class PacienteDTO {
    private String nome;
    private String email;
    private String cpf;
}
