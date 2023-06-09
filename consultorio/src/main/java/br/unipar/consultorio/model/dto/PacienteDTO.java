package br.unipar.consultorio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    private String nome;
    private String email;
    private String cpf;
}
