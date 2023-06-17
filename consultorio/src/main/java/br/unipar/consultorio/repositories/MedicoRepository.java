package br.unipar.consultorio.repositories;

import br.unipar.consultorio.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query
    public List<Medico> findAllByOrderByNomeAsc();

    @Query
    public List<Medico> findByNomeContainingIgnoreCase(String nome);

    @Query("select medicos from Medico medicos where medicos.status = 'ATIVO' and medicos.id not in (" +
            "select consulta.medico.id from Consulta consulta where consulta.dataHoraConsulta = :data)")
    public List<Medico> findMedicosDisponiveis(@Param("data") Date data);


}
