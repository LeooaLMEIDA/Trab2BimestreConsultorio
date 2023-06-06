package br.unipar.consultorio.repositories;

import br.unipar.consultorio.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query
    public List<Paciente> findByNomeContainingIgnoreCase(String nome);

}
