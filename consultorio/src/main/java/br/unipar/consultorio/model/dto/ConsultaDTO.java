package br.unipar.consultorio.model.dto;

import br.unipar.consultorio.model.Consulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO {
    private Long id;
    private String dataHora;
    private Long medico_id;
    private Long paciente_id;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static ConsultaDTO consultaDTO(Consulta consulta){
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getId());
        consultaDTO.setMedico_id(consulta.getMedico().getId());
        consultaDTO.setPaciente_id(consulta.getPaciente().getId());

        consultaDTO.setDataHora(simpleDateFormat.format(consulta.getDataHoraConsulta()));
        return consultaDTO;
    }
}
