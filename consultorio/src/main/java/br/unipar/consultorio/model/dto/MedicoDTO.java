package br.unipar.consultorio.model.dto;

import br.unipar.consultorio.enums.EspecialidadeENUM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    private String nome;
    private String email;
    private String crm;
    private EspecialidadeENUM especialidade;
}
