package br.unipar.consultorio.repositories;

import br.unipar.consultorio.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query
    public List<Medico> findAllByOrderByNomeAsc();

    @Query
    public List<Medico> findByNomeContainingIgnoreCase(String nome);
}
