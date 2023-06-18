package br.unipar.consultorio.repositories;

import br.unipar.consultorio.model.Consulta;
import br.unipar.consultorio.model.Medico;
import br.unipar.consultorio.model.Paciente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente as Consultas")
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query
    @ApiModelProperty(value = "Método utilizado para Consultar um Paciente pelo o ID e a Data")
    public List<Consulta> findConsultaByPacienteAndAndDataHoraConsulta(Paciente paciente, Date data);

    @Query
    @ApiModelProperty(value = "Método utilizado para Consultar um Médico pelo o ID e a Data")
    public List<Consulta> findConsultaByMedicoAndDataHoraConsulta(Medico medico, Date date);

}
