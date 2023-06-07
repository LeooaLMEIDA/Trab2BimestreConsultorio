package br.unipar.consultorio.repositories;

import br.unipar.consultorio.model.Consulta;
import br.unipar.consultorio.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query
    public List<Consulta> findByDataHoraConsulta(Date data);

    //Como buscar o paciente no parametro de consulta
        //R: Buscar pelo objeto inteiro -

}
