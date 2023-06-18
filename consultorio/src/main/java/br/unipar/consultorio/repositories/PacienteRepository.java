package br.unipar.consultorio.repositories;

import br.unipar.consultorio.model.Medico;
import br.unipar.consultorio.model.Paciente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente aos Pacientes")
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query
    public List<Paciente> findAllByOrderByNomeAsc();

    @Query
    public List<Paciente> findByNomeContainingIgnoreCase(String nome);

}
