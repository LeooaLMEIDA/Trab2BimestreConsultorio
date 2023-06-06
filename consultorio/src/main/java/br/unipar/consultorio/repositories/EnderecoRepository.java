package br.unipar.consultorio.repositories;

import br.unipar.consultorio.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query
    public List<Endereco> findByCepContainingIgnoreCase(String cep);
}
